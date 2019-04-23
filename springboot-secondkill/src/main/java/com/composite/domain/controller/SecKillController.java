package com.composite.master.domain.controller;

import com.composite.bo.GoodsBo;
import com.composite.entity.OrderInfo;
import com.composite.entity.SecKillOrder;
import com.composite.entity.User;
import com.composite.redis.RedisService;
import com.composite.redis.UserKey;
import com.composite.result.CodeMsg;
import com.composite.service.SecKillGoodsService;
import com.composite.service.SecKillOrderService;
import com.composite.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("secKill")
public class SecKillController {


    @Autowired
    private RedisService redisService;

    @Autowired
    private SecKillGoodsService seckillGoodsService;

    @Autowired
    private SecKillOrderService secKillOrderService;

    @RequestMapping("/secKill")
    public String list(Model model, HttpServletRequest request, @RequestParam("goodsId") long goodsId) {

        String loginToken = CookieUtil.readLoginToken(request);
        User user = redisService.get(UserKey.getByName, loginToken, User.class);
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsBo goods = seckillGoodsService.getSecKillGoodsBoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        SecKillOrder order = secKillOrderService.getSecKillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = secKillOrderService.insert(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}

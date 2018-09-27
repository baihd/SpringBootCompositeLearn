package com.composite.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SecKillGoods {
    private Long id;

    private Long goodsId;

    private BigDecimal secKillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getSecKillPrice() {
        return secKillPrice;
    }

    public void setSecKillPrice(BigDecimal secKillPrice) {
        this.secKillPrice = secKillPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SecKillGoods() {
    }

    public SecKillGoods(Long id, Long goodsId, BigDecimal secKillPrice, Integer stockCount, Date startDate, Date endDate) {
        this.id = id;
        this.goodsId = goodsId;
        this.secKillPrice = secKillPrice;
        this.stockCount = stockCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SecKillGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", secKillPrice=" + secKillPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
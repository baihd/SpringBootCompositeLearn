# WSO2 API Manager

## 一、介绍
* WSO2 API Manager是一个完整的解决方案，用于设计和发布API，创建和管理开发人员社区，以及以可扩展的方式保护和路由API流量。
* 它利用来自WSO2平台的成熟组件来保护，集成和管理API。
* 它与WSO2分析平台集成，提供现成的报告和警报，让你即时了解API行为。

## 二、安装
* 1.下载并安装OpenJDK 8或Oracle JDK 1.8。并设置JAVA_HOME环境变量
* 2.下载最新版本的WSO2 API Manager
* 3.启动，进入<API-M_HOME>/bin目录，然后执行wso2server.bat或wso2server.sh


## 三、了解API Manager概念
### 1.组件
#### API Manager包含以下组件：
* API Publisher
    * 使API提供程序能够发布API，共享文档，提供API密钥以及收集有关功能，质量和使用情况的反馈。Web界面：`https://<Server Host>:9443/publisher`。
* API Store
    * 使API使用者能够自行注册，发现和订阅API，评估它们并与API发布者进行交互。Web界面：`https://<Server Host>:9443/store`。
* API Gateway
    * 保护，维护，管理和扩展API调用。它是一个简单的API代理，可拦截API请求并应用限制和安全检查等策略。它还有助于收集API使用情况统计信息。Web界面：`https://<Server Host>:9443/carbon`。
* Key Manager
    * 处理所有安全和密钥相关操作。API网关与密钥管理器连接，以检查订阅，OAuth令牌和API调用的有效性。密钥管理器还提供令牌API以生成可通过网关访问的OAuth令牌。
* Traffic Manager
    * 帮助用户管理API流量，使不同服务级别的消费者可以使用API和应用程序，并保护API免受安全攻击。流量管理器具有动态限制引擎，可实时处理限制策略。
* WSO2 API Manager Analytics
    * 提供大量统计图表和预定事件的警报机制。
    
### 2.用户和角色
#### API管理器提供三种不同的社区角色，适用于大多数企业：
* 创建者
    * 创建者是技术角色，他了解API的技术方面（接口，文档，版本，网关如何公开等），并使用API发布者将API配置到API存储中。创建者使用API Store查询API用户提供的评级和反馈。创建者可以向商店添加API，但无法管理他们的生命周期。
* 发布者
    * 发布者管理整个企业或业务部门的一组API，并控制API生命周期和货币化方面。 
* 消费者
    * 消费者使用API Store来发现API，查看文档和论坛，以及对API进行评级/评论。消费者订阅API以获取API密钥。
    
### 3.API生命周期
* API是已发布的接口，而服务是在后端运行的实现。API有自己的生命周期，独立于它们所依赖的后端服务。此生命周期在API Publisher中公开，并由发布者角色管理。
#### 默认API生命周期中提供以下阶段：
* 已创建
    * API元数据已添加到API存储中，但尚未向订阅者显示，也未部署到API网关。
* 原型
    * API作为原型在API Store中部署和发布。原型API通常是公开的模拟实现，以获得有关其可用性的反馈。用户可以在不订阅的情况下试用原型API。
* 已发布
    * API在API商店中可见，可供订阅。
* 弃用
    * API仍然部署在API网关中（即在运行时可用于现有用户），但订阅者不可见。你可以在发布新版本时自动弃用API。
* 退休
    * API未从API网关发布，并从商店中删除。
* 阻塞
    * 访问API暂时受阻。运行时调用被阻止，API不再显示在API Store中。
    
### 4.应用
#### 应用程序主要用于将使用者与API分离。它允许你执行以下操作：
* 为多个API生成并使用单个密钥。
* 多次订阅到具有不同SLA级别的单个API。
* 创建一个订阅API的应用程序，可以根据需要创建任意数量的应用程序。API Manager附带一个默认应用程序。  
  
### 5.节流层
* 节流层在订阅时与API相关联，并且可以在API级别，资源级别，订阅级别和应用程序级别（每个令牌）定义。它们定义了API网关强制执行的限制限制，例如10TPS（每秒事务数）。授予给定API的给定用户的最终限制最终由所有限制层的合并输出一起定义。
* API Manager为每个级别提供了三个预定义的层和一个名为的特殊层Unlimited，可以通过编辑`<API-M_HOME>/repository/conf/api-manager.xml`文件的元素来禁用它们。
* 预定义的订阅层：
    * 无限：允许无限请求
    * 金：每分钟允许5000个请求
    * 银：每分钟允许2000个请求
    * 青铜：每分钟允许1000个请求
    
### 6.API密钥
#### API Manager支持两种身份验证方案：
* 应用程序访问令牌（用于标识和验证整个应用程序的访问令牌）：
  * 由API使用者生成，必须在传入的API请求中传递。
  * API Manager使用OAuth2标准提供密钥管理。API密钥是一个简单的字符串，可以使用HTTP标头（例如“Authorization: Bearer NtBQkXoKElu0H1a1fQ0DWfo6IX4a,”）传递它，它同样适用于SOAP和REST调用。
  * 应用程序访问令牌在应用程序级别生成，对应用程序关联的所有API都有效。这些令牌具有固定的到期时间，默认设置为60分钟。可以将此更改为更长的时间，甚至数周。消费者可以直接从API Store重新生成访问令牌。
  * 要更改默认的默认过期时间（默认为60分钟），打开`<API-M_HOME>/repository/conf/identity/identity.xml`文件并更改元素的值`<AccessTokenDefaultValidityPeriod>`。如果设置负值，则令牌永不过期。对此值的更改仅应用于你创建的新应用程序。
* 应用程序用户访问令牌（用于标识应用程序的最终用户的访问令牌（例如部署在许多设备上的移动应用程序的最终用户））：
  * 可以使用令牌API按需生成访问令牌。如果令牌过期，可以使用令牌API刷新它。
  * 令牌API采用以下参数来生成访问令牌：拨款类型，用户名，密码，范围。
  * 要生成新的访问令牌，请使用上述参数在其中发出令牌API调用。然后，令牌API返回两个令牌：访问令牌和刷新令牌。访问令牌保存在客户端的会话中（应用程序本身不需要管理用户和密码）。在API网关侧，为每个API调用验证访问令牌。当令牌过期时，你通过发出带有上述参数的令牌API调用来刷新令牌，并将刷新令牌作为参数传递。 `grant_type=password grant_type=refresh_token`。 
    
### 7.API资源
* API由一个或多个资源组成。每个资源处理特定类型的请求，类似于较大API中的方法（函数）。API资源接受以下可选属性：
  * verbs：指定特定资源接受的HTTP谓词。允许的值为GET，POST，PUT，DELETE，PATCH，HEAD和OPTIONS。你可以一次提供多个值。  
  * uri-template：`http ://tools.ietf.org/html/rfc6570`中定义的URI模板。（例如，`/phoneverify/<phoneNumber>`)。
  * url-mapping：根据servlet规范定义的URL映射（扩展映射，路径映射和精确映射）。
  * 限制层：限制在给定时间段内对资源的命中数。
  * Auth-Type：指定HTTP谓词的资源级别身份验证。Auth-type可以是None，Application，Application User或Application＆Application User。  
  * 无：可以访问特定的API资源而无需任何访问令牌。
  * 应用程序：访问API资源需要应用程序访问令牌。
  * 应用程序用户：访问API资源需要用户访问令牌。
  * 应用程序和应用程序用户：访问API资源需要应用程序访问令牌和用户访问令牌。
  
  
## 四、创建用户和角色
* 在用户和角色中，我们引入了一组在许多企业中常见的用户。让我们看看如何以管理员身份登录管理控制台并创建这些角色。
  * 1.使用凭据（admin/admin）登录管理控制台：`https://<hostname>:9443/carbon`。 
  * 2.单击Main菜单下的Users and Roles部分中的Add。
    ![avatar](/wso2/wso2_04_01.png)
    * 默认情况下，创建者，发布者和订阅者角色可用，如下所示。
    ![avatar](/wso2/wso2_04_02.png)
  * 3.单击“Add New Role”。
    ![avatar](/wso2/wso2_04_03.png)
  * 4.将角色名称设为creator，然后单击“Next”。
    ![avatar](/wso2/wso2_04_04.png)
  * 5.权限列表将打开。选择以下内容并单击“Finish”。
    * All Permissions > Admin Permissions > Configure > Governance and all underlying permissions
    * All Permissions > Admin Permissions > Login
    * All Permissions > Admin Permissions > Manage > API > Create
    * All Permissions > Admin Permissions > Manage > Resources > Govern and all underlying permissions
    ![avatar](/wso2/wso2_04_05.png)
  * 6.同样，publisher使用以下权限创建角色。
    * All Permissions > Admin Permissions > Login
    * All Permissions > Admin Permissions > Manage > API > Publish
  * 7.API Manager默认提供角色subscriber。它具有以下权限：
    * All Permissions > Admin Permissions > Login
    * All Permissions > Admin Permissions > Manage > API > Subscribe
  * 8.添加的角色（创建者和发布者）现在显示在角色下。让我们为每个角色创建用户。
    ![avatar](/wso2/wso2_04_06.png)
  * 9.单击Main菜单下的Users and Roles部分中的Add。
    ![avatar](/wso2/wso2_04_07.png)
  * 10.单击“Add New User”。
    ![avatar](/wso2/wso2_04_08.png)
  * 11.提供用户名/密码并单击“Next”。例如，让我们按名称创建一个新用户apipublisher。
    ![avatar](/wso2/wso2_04_09.png)
  * 12.选择要分配给用户的角色（例如，publisher），然后单击“Finish”。
    ![avatar](/wso2/wso2_04_10.png)
  * 13.同样，按名称创建新用户apicreator并分配创建者角色。
  
## 五、从头开始创建API
* 1.用API发布者（apicreator）登录：`https://<hostname>:9443/publisher`
* 2.在APIS菜单中，单击“添加新API”。
    ![avatar](/wso2/wso2_05_01.png)
* 3.选择设计新API的选项，然后单击“开始创建”。
    ![avatar](/wso2/wso2_05_02.png)
* 4.提供下面信息：
    | Field | Sample value | 
    | ------ | ------ | 
    | Name | PhoneVerification |
    | Context | /phoneverify | 
    | Version | 1.0.0 | 
    | Access Control | 
    | Visibility on Store | Public | 
    | API Definition | URL pattern：CheckPhoneNumber | 
    * 单击“Add”，然后单击“Next: Implement”以继续下一页。
    ![avatar](/wso2/wso2_05_03.png)
* 5.选择Managed API选项。
    ![avatar](/wso2/wso2_05_04.png)
    * 完成后单击“Next:Manage”>。
* 6.提供以下信息：
    | Field | Sample value | 
    | ------ | ------ | 
    | Endpoint type | HTTP/REST Endpoint |
    | Production endpoint | Endpoint是`http://ws.cdyne.com/phoneverify/phoneverify.asmx`。要验证URL，请单击 旁边的“测试”按钮。在此示例中，我们使用Cdyne服务提供商公开的电话验证服务。此服务具有SOAP和REST接口。此示例服务有两个操作：CheckPhoneNumber和CheckPhoneNumbers。| 
    | Sandbox endpoint | Endpoint是`http://ws.cdyne.com/phoneverify/phoneverify.asmx`。要验证URL，请单击旁边的“测试”按钮。 | 
    ![avatar](/wso2/wso2_05_05.png)
    * 完成后单击“Next:Manage”>。
* 7.在“Manage”选项卡中提供以下信息。保留UI中其余参数的默认值。完成后，单击“保存”。 
    | Field | Value | Description |
    | ------ | ------ | ------ | 
    | 订阅层 | 选择所有可用层 |API可以在不同的服务级别进行订阅。它们允许你限制在给定时间段内成功点击API的次数。|
    ![avatar](/wso2/wso2_05_06.png)

## 六、添加API文档
* 1.在APIS菜单中，单击API的缩略图以将其打开。 
* 2.单击API的文档选项卡，然后单击添加新文档。
    ![avatar](/wso2/wso2_06_01.png)
* 3.出现文档选项。请注意，您可以通过URL或文件内联创建文档。对于内联文档，您可以直接从API发布者界面编辑内容。你有几种文件类型：
    * How To
    * 样本和SDK
    * 公共论坛/支持论坛（仅限外部链接）
    * API消息格式
    * 其他
* 4.创建一个“How To”命名PhoneVerification，指定内联内容作为源，并可选择输入摘要。完成后，单击“添加文档”。
    ![avatar](/wso2/wso2_06_02.png)
* 5.添加文档后，单击“编辑内容”以打开嵌入式编辑器。
    ![avatar](/wso2/wso2_06_03.png)
* 6.输入您的API文档，然后单击“保存并关闭”。
    ![avatar](/wso2/wso2_06_04.png)
    
## 七、对API进行版本控制
* 1.用API发布者（apicreator）登录：`https://<hostname>:9443/publisher`
* 2.单击PhoneVerificationAPI将其打开，然后单击“创建新版本”。
    ![avatar](/wso2/wso2_07_01.png)
* 3.提供新版本号（例如，2.0.0）并单击“完成”。
    ![avatar](/wso2/wso2_07_02.png)
* 4.请注意，API的新版本是在API Publisher中创建的。


## 八、将范围与API资源相关联
* 不同的API资源可以与不同的用户角色相关联。要将范围映射到API资源，应执行以下操作：
  * a.首先通过单击“管理”选项卡中的“添加范围”来创建范围。
    ![avatar](/wso2/wso2_08_01.png)
  * b.在弹出的对话框中填写与范围相关的信息。请注意，范围键和角色是最重要的属性。单击右侧底部的“添加范围”按钮。
    ![avatar](/wso2/wso2_08_02.png)
    * 此处添加的角色将针对用户存储进行验证，以检查它们是否存在。可以重写此操作，以便不在用户存储中检查角色。在服务器启动时将Java系统属性disableRoleValidationAtScopeCreation设置为true：
       * i.打开`<API-M_HOME>/bin/wso2server`（sh|bat）文件。
       * ii.在文件末尾添加-DdisableRoleValidationAtScopeCreation = true。
       * iii.重启服务器。
  * c.现在，带有键“item_view”的范围随角色管理器和代理一起添加。要将此范围与/time资源上的get操作相关联，请单击资源右侧的+Scope标记。
    ![avatar](/wso2/wso2_08_03.png)
  * d.从显示的下拉菜单中，选择范围名称，然后单击右侧的勾选标记。现在，范围将与资源/项目上的GET操作相关联。
    ![avatar](/wso2/wso2_08_04.png)

## 九、发布API
* 1.用API发布者（apipublisher）登录：`https://<hostname>:9443/publisher`，然后单击PhoneVerification API的2.0.0版。
    ![avatar](/wso2/wso2_09_01.png)
* 2.API打开，转到其“Lifecycle”选项卡，然后单击“Publish”。复选框表示以下内容：
    ![avatar](/wso2/wso2_09_02.png)
* 3.转到API Store（`https://<hostname>:9443/store`），PhoneVerification 2.0.0 API在API菜单下可见。
    ![avatar](/wso2/wso2_09_03.png)

## 十、订阅API
* 1.转到API商店`https://<hostname>:9443/store`，并使用注册链接创建帐户。
    * 默认情况下，通过API Store注册的用户将分配该subscriber角色。因此，您无需通过管理控制台指定角色，以便能够将API包含在API中。
    ![avatar](/wso2/wso2_10_01.png)
* 2.填写“注册”表单中的详细信息，然后单击“注册”。
    * 通过登录管理控制台`https：//localhost：9443/carbon`，并访问用户和角色>用户>列表，可以查看使用API Store注册注册的用户。
    ![avatar](/wso2/wso2_10_02.png)
    * 注册中输入的详细信息将在管理控制台中与每个用户相关的默认配置文件中更新。
    ![avatar](/wso2/wso2_10_03.png)
* 3.注册后，登录API Store并单击PhoneVerification 2.0.0 您之前发布的API。
* 4.请注意，您现在可以看到订阅选项。选择默认应用程序和Bronze层。单击订阅。
    ![avatar](/wso2/wso2_10_04.png)
* 5.订阅成功后，单击显示的信息消息中的查看订阅以查看订阅。
    ![avatar](/wso2/wso2_10_05.png)
* 6.单击应用程序的“生产密钥”选项卡，然后单击“生成密钥”以生成稍后用于调用API的访问令牌。如果您之前已生成密钥，请单击“重新生成”。
    * 提示：您可以在给定的文本框中设置令牌有效期。默认情况下，它设置为一小时。如果设置负值（例如，-1），则令牌将永不过期。
    ![avatar](/wso2/wso2_10_06.png)

## 十一、调用API
* 1.单击API Store中的API菜单，然后单击要调用的API。API打开后，转到其API控制台选项卡。
    ![avatar](/wso2/wso2_11_01.png)
* 2.展开资源的GET方法CheckPhoneNumber。请注意，您在创建交互式文档时添加的参数现在会显示其描述，以便作为订阅者，您知道如何调用此API。
    ![avatar](/wso2/wso2_11_02.png) 
* 3.为PhoneNumber and提供示例值，LicenseKey然后单击Try it out以调用API。
    ![avatar](/wso2/wso2_11_03.png) 
* 4.请注意API调用的响应。由于我们在此示例中使用了有效的电话号码，因此响应有效。
    ![avatar](/wso2/wso2_11_04.png) 
* 5.您已使用API控制台调用了API。
    
## 十二、使用API Publisher URLs映射后端URL的参数
* 1.本章介绍如何将后端URL映射到API Publisher中所需的模式。请注意以下事项：
  * a.发布服务器中API的URL模式是`http://<hostname>:8280/<context>/<version>/<API resource>`
  * b.可以将变量定义为API资源的URI模板的一部分。例如，在URI模板中/{businessId}，businessId是一个变量
  * c.在中介运行时期间使用带有“uri.var.”前缀的属性值读取资源中的变量
    * 例如`http://localhost:8080/businesses/{uri.var.businessId}/details`，此HTTP端点获取你在资源中指定的businessId
  * d.API资源的URI模板在运行时自动附加到HTTP端点的末尾
    * 可以使用以下中间件设置从后端端点删除URL后缀：`<property name="REST_URL_POSTFIX" scope="axis2" action="remove"/>`
* 2.上传Sequence文件到AM，以下是名为removePostfixSequence.xml的Sequence文件
```
<sequence xmlns="http://ws.apache.org/ns/synapse" name="RemovePostfixSequence">
 <property name="REST_URL_POSTFIX" scope="axis2" action="remove"/>
</sequence>
```
   * a.登陆API carbon`https://localhost:9443/carbon/`，用户名密码默认为admin/admin
   * b.选择 Resource > Browse > _system > governance > apimgt > customsequences > in
    ![avatar](/wso2/wso2_12_01.png)
   * c.上传removePostfixSequence.xml文件，点击“Add Resource”，在File栏中选择Browse，选择removePostfixSequence.xml文件的路径，点击Add。
    ![avatar](/wso2/wso2_12_02.png)
   * d.上传成功后，可以在Entries中看到已上传的文件
    ![avatar](/wso2/wso2_12_03.png)
* 3.添加API到AM
   * a.登录API Publisher，设计包含以下信息的新API，单击“Add”，然后单击“Next: Implement”
    | Field | Sample value |
    | ------ | ------ | 
    | Name | TestAPI |
    | Context | /test |
    | Version | 1.0.0 |
    | Visibility | Public |
    | Resources | URL pattern：/{businessId}，Reques types：GET|
    ![avatar](/wso2/wso2_12_04.png)
    ![avatar](/wso2/wso2_12_05.png)
   * b.“Implement”选项卡打开。提供下表中的信息。 选择Enable Message Mediation,在In Flow下选择RemovePostfixSequence
    | Field | Sample value |
    | ------ | ------ | 
    | Endpoint type | HTTP/REST endpoint |
    | Production endpoint | `http://localhost:8080/businesses/{uri.var.businessId}/details` |
    | Sandbox endpoint | `http://localhost:8080/businesses/{uri.var.businessId}/details` |
     ![avatar](/wso2/wso2_12_06.png)
   * c.单击 Next: Manage>  转到  Manage 选项卡，选择Gold层并发布API
     ![avatar](/wso2/wso2_12_07.png)
   * d.登录API Store并订阅API
     ![avatar](/wso2/wso2_12_08.png)
   * e.出现提示时，单击“View Subscriptions”按钮。“Subscriptions”选项卡打开
   * f.单击“Production Keys”选项卡，然后单击“Generate Keys”以创建应用程序访问令牌。如果你之前已生成令牌，请单击“Re-generate”以续订访问令牌
     ![avatar](/wso2/wso2_12_09.png)
   * g.单击API的API Console选项卡
     ![avatar](/wso2/wso2_12_10.png)
   * h.请注意，它businessId作为参数添加到UI中。填写一个businessId，然后点击Try it out调用API
     ![avatar](/wso2/wso2_12_11.png)

## 十三、创建一个新的API版本
* 1.登录WSO2 API发布者，`https://<hostname>:9443/publisher`
* 2.单击与要为其创建新版本的API对应的API名称
* 3.单击“创建新版本”
    ![avatar](/wso2/wso2_13_01.png)
* 4.输入版本号，选择默认版本选项，然后单击“完成”
    ![avatar](/wso2/wso2_13_02.png)
* 5.单击新API版本的“编辑”图标进行编辑
    ![avatar](/wso2/wso2_13_03.png)
* 6.对API进行必要的修改
* 7.编辑完成后，单击“保存”
    * 默认情况下，API Store中仅显示最新版本的API。如果要显示多个版本，请将`<DisplayMultipleVersions>`元素设置为true。`<API-M_HOME>/repository/conf/api-manager.xml`文件，然后重新启动服务器。

## 十四、创建和发布SOAP API
* 1.登录API Publisher并单击“ADD NEW API”
    ![avatar](/wso2/wso2_14_01.png)
* 2.选择I Have a SOAP Endpoint以便使用现有SOAP端点设计API，然后选择Pass Through，并选择以下选项之一：
    * WSDL URL - 如果选择此选项，则需要提供端点URL
    * WSDL存​​档/文件 - 如果选择此选项，请单击“Browse”并上传单个WSDL文件或WSDL存​​档，这是一个具有多个WSDL的WSDL项目
* 3.单击Start Creating
* 4.继续完成创建和发布API
    * a.API的“Design”选项卡打开。提供下表中的信息，然后单击Next: Implement>以继续执行阶段。
    | Field | Sample value |
    | ------ | ------ | 
    | Name | Students |
    | Context | /getStudent |
    | Version | 1.0.0 |
    ![avatar](/wso2/wso2_14_02.png)
    * b.单击“Managed API”选项。
    * c.选择HTTP/SOAP Endpoint，提供生产端点`http://127.0.0.1:8082/webservice/api/v1/student/get?wsdl`，然后单击Manage。 
    ![avatar](/wso2/wso2_14_03.png)
    * d.在“Manage”选项卡中，选择“Gold”，向下滚动并单击“Save & Publish”。现在已将SOAP API发布到API Store。我们订阅它。
    ![avatar](/wso2/wso2_14_04.png)
* 5.出现提示时，选择在API Store中打开新发布的API
* 6.选择一个应用程序，Gold层并订阅API
    ![avatar](/wso2/wso2_14_05.png)
* 7.出现提示时，单击“View Subscriptions”按钮。“Subscriptions”选项卡打开
* 8.单击“Production Keys”选项卡，然后单击“Generate Keys”以创建应用程序访问令牌。如果你之前已生成密钥，请单击“Regenerate”。默认情况下，访问令牌在创建后一小时后到期，除非你更改到期时间。调用API
    ![avatar](/wso2/wso2_14_06.png)
* 9.在APIs菜单上，选择API并单击API Console选项卡
    ![avatar](/wso2/wso2_14_07.png)
* 10.展开POST方法，然后单击“Try it out”。输入以下内容，然后单击“Execute”以调用API
    * SOAP动作：`http://service.cxf.composite.com/getStudent`
    * SOAP请求：
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.cxf.composite.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:getStudent>
         <id>1</id>
      </ser:getStudent>
   </soapenv:Body>
</soapenv:Envelope>         
```
* 11.请注意控制台上显示的API响应。
    ![avatar](/wso2/wso2_14_08.png) 
    
## 十五、从SOAP后端生成REST API 
* 确保你拥有SOAP后端的有效WSDL URL，协议为SOAP1.2
* 1.登录API Publisher，然后单击API菜单下的ADD NEW API
* 2.选择I Have a SOAP Endpoint。你将看到以下两个选项来为SOAP后端创建API
    * Pass Through  - 为进入API网关的SOAP请求创建传递代理
    * Generate REST APIs - 此选项用于从给定的WSDL URL生成REST API定义
* 3.选择Generate REST API。提供SOAP后端的WSDL URL，然后单击“Start Creating”
    ![avatar](/wso2/wso2_15_01.png) 
* 4.单击Edit Source以编辑API的Swagger规范。自动生成的API的REST资源显示出来。可以通过修改此页面上显示的Open API规范来更好地描述REST API。以下是如何描述此特定API的示例。
    ![avatar](/wso2/wso2_15_02.png) 
* 5.（可选）更新API的Swagger定义，然后单击Apply Changes以保存API。
    * 让我们通过用以下Swagger定义替换现有的Swagger定义来更新API
    ![avatar](/wso2/wso2_15_03.png) 
* 6.生成的API定义将添加到API，如下所示
    ![avatar](/wso2/wso2_15_04.png) 
* 7.编辑API的详细信息，如下所示
    | Field | Sample value |
    | ------ | ------ | 
    | Name | Students |
    | Context | /getStudent |
    | Version | 1.0.0 |
    | Access Control | All |
    | Visibility on Store | Public |
    | Tags | Students |
        
* 8.转到“Implement”选项卡。单击Managed API
* 9.选择端点类型作为HTTP/SOAP端点。输入SOAP端点URL
    ![avatar](/wso2/wso2_15_05.png) 
* 10.导航到SOAP to REST Mapping部分。单击资源以查看API的In和Out序列
    * 以下是基于Synapse的XML配置示例，它从请求路径中读取一些参数，并构造后端（目标）服务所需的SOAP有效负载
    ![avatar](/wso2/wso2_15_06.png) 
* 11.转到“Manage”选项卡，然后选择API的限制层。单击“Publish”以将API 发布到API Store
* 12.导航到API Store并subscribe to an API
* 13.调用API

## 十六、作为原型部署和测试
* 1.登录WSO2 API Publisher并选择PhoneVerification 2.0.0为要原型化的API
    ![avatar](/wso2/wso2_16_01.png)
* 2.单击“转到概述”
* 3.单击API的“生命周期”选项卡，然后单击“部署为原型”。创建新版本后，通常将其部署为原型，以便进行测试和早期升级
    ![avatar](/wso2/wso2_16_02.png)
* 4.登录API Store并单击新原型API。`https://<hostname>:9443/store` 
    * 将打开“API概述”页面。请注意以下事项： 
      * 没有订阅选项
      * 有两组URL（有和没有版本）
      * 其他功能，如文档，社交媒体和论坛可用
* 5.单击API控制台选项卡，调用原型API
* 6.在原型API的API控制台中，展开方法，单击“试用”
* 7.单击“执行”以调用API
    * 请注意控制台中显示的响应。你不必订阅API或传递授权密钥来调用原型API
    ![avatar](/wso2/wso2_16_03.png)
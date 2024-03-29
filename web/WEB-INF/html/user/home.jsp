<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-10-27
  Time: 下午 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/global.css">
</head>
<body style="margin-top: 65px;">

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <img src="${pageContext.servletContext.contextPath}/res/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="/"><i class="iconfont icon-jiaoliu"></i>交流</a>
            </li>
            <li class="layui-nav-item">
                <a href="../case/case.html"><i class="iconfont icon-iconmingxinganli"></i>案例</a>
            </li>
            <li class="layui-nav-item">
                <a href="http://www.layui.com/" target="_blank"><i class="iconfont icon-ui"></i>框架</a>
            </li>
        </ul>

        <ul class="layui-nav fly-nav-user">
            <!-- 登入后的状态 -->
            <c:if test="${user!=null}">
                <li class="layui-nav-item">
                    <a class="fly-nav-avatar" href="javascript:;">
                        <cite class="layui-hide-xs">${user.nickname}</cite>
                        <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
                        <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>
                        <img src="${pageContext.servletContext.contextPath}/images/avatar/${user.avatar}">
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.servletContext.contextPath}/user/set"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                        <dd><a href="user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
                        <dd><a href="${pageContext.servletContext.contextPath}/user/home"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
                        <hr style="margin: 5px 0;">
                        <dd><a id="logoutbtn"  href="${pageContext.servletContext.contextPath}/logout" style="text-align: center;">退出</a></dd>
                    </dl>
                </li>
            </c:if>
        </ul>
    </div>
</div>

<div class="fly-home fly-panel" style="background-image: url();">
    <c:if test="${user!=null}">
    <img src="${pageContext.servletContext.contextPath}/images/avatar/${user.avatar}" alt="${user.nickname}">
    <i class="iconfont icon-renzheng" title="Fly社区认证"></i>
    <h1>
            ${user.nickname}
        <i class="iconfont icon-nan"></i>
        <!-- <i class="iconfont icon-nv"></i>  -->
        <i class="layui-badge fly-badge-vip">VIP3</i>
        <!--
        <span style="color:#c00;">（管理员）</span>
        <span style="color:#5FB878;">（社区之光）</span>
        <span>（该号已被封）</span>
        -->
<%--    </h1>--%>

<%--    <p style="padding: 10px 0; color: #5FB878;">认证信息：layui 作者</p>--%>

    <p class="fly-home-info">
        <i class="iconfont icon-kiss" title="飞吻"></i><span style="color: #FF7200;">${user.kissnum} 飞吻</span>
        <i class="iconfont icon-shijian"></i><span>${user.regtime} 加入</span>
        <i class="iconfont icon-chengshi"></i><span>来自${user.city}</span>
    </p>

    <p class="fly-home-sign">${user.sign}</p>
    </c:if>
    <div class="fly-sns" data-user="">
        <a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">加为好友</a>
        <a href="javascript:;" class="layui-btn layui-btn-normal fly-imActive" data-type="chat">发起会话</a>
    </div>

</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6 fly-home-jie">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.nickname} 最近的提问</h3>
                <ul class="jie-row">
                    <c:if test="${homeloaderinfo!=null}">
                        <c:forEach items="${homeloaderinfo.publishedList}" var="publish">
                    <li>
                        <span class="fly-jing">${publish.catenameZh}</span>
                        <a href="${pageContext.servletContext.contextPath}/detail?id=${publish.id}" class="jie-title"> ${publish.title}</a>
                        <i>${publish.publishTime}</i>
                        <em class="layui-hide-xs">${publish.views}阅/${publish.replyNum}答</em>
                    </li>
                        </c:forEach>
                    </c:if>
<%--                    <li>--%>
<%--                        <a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>--%>
<%--                        <i>1天前</i>--%>
<%--                        <em class="layui-hide-xs">1136阅/27答</em>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>--%>
<%--                        <i>2017-10-30</i>--%>
<%--                        <em class="layui-hide-xs">1136阅/27答</em>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>--%>
<%--                        <i>1天前</i>--%>
<%--                        <em class="layui-hide-xs">1136阅/27答</em>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>--%>
<%--                        <i>1天前</i>--%>
<%--                        <em class="layui-hide-xs">1136阅/27答</em>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>--%>
<%--                        <i>1天前</i>--%>
<%--                        <em class="layui-hide-xs">1136阅/27答</em>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>--%>
<%--                        <i>1天前</i>--%>
<%--                        <em class="layui-hide-xs">1136阅/27答</em>--%>
<%--                    </li>--%>
                    <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div> -->
                </ul>
            </div>
        </div>

        <div class="layui-col-md6 fly-home-da">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.nickname} 最近的回答</h3>
                <ul class="home-jieda">
<c:if test="${homeloaderinfo!=null}">
    <c:forEach items="${homeloaderinfo.replyList}" var="reply">
                    <li>
                        <p>
                            <span>${reply.replyTime}</span>
                            在<a href="${pageContext.servletContext.contextPath}/detail?id=${reply.aid}" target="_blank">${reply.title}</a>中回答：
                        </p>
                        <div class="home-dacontent">
        ${reply.replyContent}
                        </div>
                    </li>
    </c:forEach>
</c:if>
<%--                    <li>--%>
<%--                        <p>--%>
<%--                            <span>5分钟前</span>--%>
<%--                            在<a href="" target="_blank">在Fly社区用的是什么系统啊?</a>中回答：--%>
<%--                        </p>--%>
<%--                        <div class="home-dacontent">--%>
<%--                            Fly社区采用的是NodeJS。分享出来的只是前端模版--%>
<%--                        </div>--%>
<%--                    </li>--%>

                    <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> -->
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="fly-footer">
    <p><a href="http://fly.layui.com/" target="_blank">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">layui.com 出品</a></p>
    <p>
        <a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a>
        <a href="http://www.layui.com/template/fly/" target="_blank">获取Fly社区模版</a>
        <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
    </p>
</div>

<script src="${pageContext.servletContext.contextPath}/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'user';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '${pageContext.servletContext.contextPath}/res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '${pageContext.servletContext.contextPath}/res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>
<script src="${pageContext.servletContext.contextPath}/logout.js"  charset="utf-8"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/22
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Fly Template v3.0，基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/global.css">
</head>
<body>

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

            <!-- 未登入的状态 -->
            <c:if test="${user==null}">
                <li class="layui-nav-item">
                    <a class="iconfont icon-touxiang layui-hide-xs" href="${pageContext.servletContext.contextPath}/login"></a>
                </li>
                <li class="layui-nav-item">
                    <a href="${pageContext.servletContext.contextPath}/login">登入</a>
                </li>
                <li class="layui-nav-item">
                    <a href="${pageContext.servletContext.contextPath}/reg">注册</a>
                </li>
                <li class="layui-nav-item layui-hide-xs">
                    <a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>
                </li>
                <li class="layui-nav-item layui-hide-xs">
                    <a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>
                </li>
            </c:if>


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
                        <dd><a href="user/set.html"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                        <dd><a href="user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
                        <dd><a href="user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
                        <hr style="margin: 5px 0;">
                        <dd><a id="logoutbtn" href="${pageContext.servletContext.contextPath}/logout" style="text-align: center;">退出</a></dd>
                    </dl>
                </li>
            </c:if>
        </ul>
    </div>
</div>

<div class="layui-hide-xs">
    <div class="fly-panel fly-column">
        <div class="layui-container">
            <ul class="layui-clear">
                <li class="layui-hide-xs"><a href="${pageContext.servletContext.contextPath}/index">首页</a></li>
                <li class="layui-this"><a href="">提问</a></li>
                <li><a href="">分享<span class="layui-badge-dot"></span></a></li>
                <li><a href="">讨论</a></li>
                <li><a href="">建议</a></li>
                <li><a href="">公告</a></li>
                <li><a href="">动态</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="../user/index.html">我发表的贴</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="../user/index.html#collection">我收藏的贴</a></li>
            </ul>

            <div class="fly-column-right layui-hide-xs">
                <span class="fly-search"><i class="layui-icon"></i></span>
                <a href="add.html" class="layui-btn">发表新帖</a>
            </div>
            <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
                <a href="add.html" class="layui-btn">发表新帖</a>
            </div>
        </div>
    </div>
</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <c:if test="${article!=null}">

            <div class="fly-panel detail-box">
                <h1>${article.title}</h1>
                <div class="fly-detail-info">
                    <!-- <span class="layui-badge">审核中</span> -->
                    <span class="layui-badge layui-bg-green fly-detail-column">${article.catenameZh}</span>

                    <c:if test="${article.end==false}">
                    <span class="layui-badge" style="background-color: #999;">未结</span>
                    </c:if>
                    <c:if test="${article.end==true}">
                    <span class="layui-badge" style="background-color: #5FB878;">已结</span>
                    </c:if>
                    <c:if test="${article.top==true}">
                    <span class="layui-badge layui-bg-black">置顶</span>
                    </c:if>
                    <c:if test="${article.cream==true}">
                    <span class="layui-badge layui-bg-red">精帖</span>
                    </c:if>
                    <div class="fly-admin-box" data-id="123">
                        <c:if test="${user!=null&&user.id==article.user.id}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                        </c:if>
                        <c:if test="${user!=null&& user.id!=article.user.id}">
                        <c:forEach items="${user.roles}" var="roles">
                        <c:if test="${roles.id==1}">
                            <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                        </c:if>
                        </c:forEach>
                        </c:if>
                        <c:if test="${user!=null&&article.top==false}">
                        <c:forEach items="${user.roles}" var="roles">
                            <c:if test="${roles.id==1}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                            </c:if>
                        </c:forEach>
                        </c:if>
                        <c:if test="${user!=null&& article.top==true}">
                            <c:forEach items="${user.roles}" var="roles">
                            <c:if test="${roles.id==1}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span>
                            </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${user!=null&& article.cream==false}">
                            <c:forEach items="${user.roles}" var="roles">
                                <c:if test="${roles.id==1}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>
                            </c:if>
                        </c:forEach>
                        </c:if>
                        <c:if test="${user!=null&& article.cream==true}">
                            <c:forEach items="${user.roles}" var="roles">
                                <c:if test="${roles.id==1}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span>
                            </c:if>
                        </c:forEach>
                        </c:if>
                    </div>
                    <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i>${article.replyNum}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${article.views}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="${pageContext.servletContext.contextPath}/images/avatar/${article.user.avatar}" alt="${article.user.nickname}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite>${article.user.nickname}</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                            <i class="layui-badge fly-badge-vip">VIP3</i>
                        </a>
                        <span>${article.publishTime}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span style="padding-right: 10px; color: #FF7200">悬赏：${article.payKiss}飞吻</span>
                        <c:if test="${user!=null&&user.id==article.uid}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                        </c:if>
                    </div>
                </div>
                <div class="detail-body photos">
                    <p>
                            ${article.content}
                    </p>

                </div>
            </div>

            </c:if>
            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <c:if test="${article!=null}">
                        <c:forEach items="${article.replyList}" var="reply">
                    <li data-id="111" class="jieda-daan">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="${pageContext.servletContext.contextPath}/images/avatar/${reply.user.avatar}" alt=" ${reply.user.nickname}">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>${reply.user.nickname}</cite>
                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                    <i class="layui-badge fly-badge-vip">VIP3</i>
                                </a>
                                <c:if test="${reply.user.id == article.user.id}">
                                <span>(楼主)</span>
                                </c:if>
                                <!--
                                <span style="color:#5FB878">(管理员)</span>
                                <span style="color:#FF9E3F">（社区之光）</span>
                                <span style="color:#999">（该号已被封）</span>
                                -->
                            </div>

                            <div class="detail-hits">
                                <span>${reply.replyTime}</span>
                            </div>
                            <c:if test="${reply.accepted==true}">
                            <i class="iconfont icon-caina" title="最佳答案"></i>
                            </c:if>
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p>${reply.replyContent}</p>
                        </div>
                        <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>${reply.goodNum}</em>
              </span>
                            <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
                            <div class="jieda-admin">
                                <c:if test="${user.id==reply.user.id}">
                                <span type="edit">编辑</span>
                                <span type="del">删除</span>
                                </c:if>
                                <c:if test="${user.id==article.user.id}">
                                    <c:if test="${reply.accepted==false}">
                                 <span class="jieda-accept" type="accept">采纳</span>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </li>
                        </c:forEach>
                    </c:if>

                    <c:if test="${article.replyList.size()==0}">
                    <!-- 无数据时 -->
                     <li class="fly-none">消灭零回复</li>
                    </c:if>
                </ul>
                <div class="layui-form layui-form-pane">
                    <form action="${pageContext.servletContext.contextPath}/reply" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="id" value="${article.id}">
                            <button class="layui-btn" >提交回复</button>
                            <span style="color: red">${replyfail}</span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <c:if test="${hotreply.size()!=0}">
                    <c:forEach items="${hotreply}" var="hotreply">
                        <dd>
                            <a href="${pageContext.servletContext.contextPath}/detail?id=${hotreply.id}">${hotreply.title}</a>
                            <span><i class="iconfont icon-pinglun1"></i>${hotreply.replyNum}</span>
                        </dd>
                    </c:forEach>
                </c:if>
                <c:if test="${indexInfo.hotReplyArticleList.size()==0}">
                    <!-- 无数据时 -->
                    <div class="fly-none">没有相关数据</div>
                </c:if>

                <!-- 无数据时 -->
                <!--
                <div class="fly-none">没有相关数据</div>
                -->
            </dl>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    这里可作为广告区域
                </div>
                <div class="fly-panel-main">
                    <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu" time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
                </div>
            </div>

            <div class="fly-panel" style="padding: 20px 0; text-align: center;">
                <img src="${pageContext.servletContext.contextPath}/res/images/weixin.jpg" style="max-width: 100%;" alt="layui">
                <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
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
    layui.cache.page = 'jie';
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
    }).use(['fly', 'face'], function(){
        var $ = layui.$
            ,fly = layui.fly;
        //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
        /*
        $('.detail-body').each(function(){
          var othis = $(this), html = othis.html();
          othis.html(fly.content(html));
        });
        */
    });

</script>
<script src="${pageContext.servletContext.contextPath}/logout.js"  charset="utf-8"></script>

</body>
</html>
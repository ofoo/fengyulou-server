<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="机器列表">
    <#include "../common/header-script.ftl">
</head>
<body>
<div class="admin">
    <#include "../common/layout-left.ftl">
    <div class="admin-right">
        <#include "../common/layout-navtitle.ftl">
        <form class="search-from" method="post" action="/fyl/computer/list/page">
            <input type="hidden" name="pageNum" id="pageNum">
            <div class="row form-group">
                <div class="col-md-3">
                    <input name="projectName" id="projectName" type="text" class="form-control" value="${(data.projectName)!}" placeholder="项目名称">
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-info" data-page="1" id="fengyulou-search">查询</button>
                    <button type="button" class="btn btn-warning" id="fengyulou-clear">清空</button>
                </div>
            </div>
        </form>
        <div class="fun-btn btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-success" id="fengyulou-insert">添加</button>
            <button type="button" class="btn btn-primary" id="fengyulou-update">修改</button>
            <button type="button" class="btn btn-danger" id="fengyulou-delete">删除</button>
        </div>
        <form id="dataForm"></form>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th width="2%"><input type="checkbox" class="checkall"></th>
                <th width="15%"><strong>项目名称</strong></th>
                <th width="15%"><strong>主机</strong></th>
                <th width="15%"><strong>端口</strong></th>
                <th width="15%"><strong>账号</strong></th>
                <th><strong>密码</strong></th>
            </tr>
            </thead>
            <tbody>
            <#list pageInfo.list as data>
            <tr>
                <td><input type="checkbox" name="ids" value="${data.id}" class="checkbox"></td>
                <td><span class="label label-success">${(data.projectName)!}</span></td>
                <td>${(data.host)!}</td>
                <td><span class="text-danger">${(data.port)!}</span></td>
                <td>${(data.account)!}</td>
                <td>${(data.password)!}</td>
            </tr>
            </#list>
            </tbody>
        </table>
        <#include "../common/layout-page.ftl">
    </div>
</div>

<#include "../common/footer-script.ftl">
<script>
    $(function(){
        // 添加
        $('#fengyulou-insert').on('click', function () {
            openPage('/fyl/computer/insert')
        })
        // 修改
        $('#fengyulou-update').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            var id = $(".checkbox:checked")[0].value;
            openPage('/fyl/computer/update?id=' + id)
        })
        // 删除
        $('#fengyulou-delete').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            delFun('/fyl/computer/ajax/delete', $("#dataForm").serialize(), function (data) {
                msgFunCallBack(data.msg,function(){
                    if (data.status == 0) {
                        location.reload()
                    }
                })
            })
        })
    })
</script>
</body>
</html>
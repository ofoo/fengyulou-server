<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="任务列表">
    <#include "../common/header-script.ftl">
</head>
<body>
<div class="admin">
    <#include "../common/layout-left.ftl">
    <div class="admin-right">
        <#include "../common/layout-navtitle.ftl">
        <form class="search-from" method="post" action="/fyl/task/list/page">
            <input type="hidden" name="pageNum" id="pageNum">
            <div class="row form-group">
                <div class="col-md-2">
                    <input name="projectName" id="projectName" type="text" class="form-control" value="${(data.projectName)!}" placeholder="项目名称">
                </div>
                <div class="col-md-2">
                    <input name="memberName" id="memberName" type="text" class="form-control" value="${(data.memberName)!}" placeholder="执行人">
                </div>
                <div class="col-md-2">
                    <input name="taskLabelName" id="taskLabelName" type="text" class="form-control" value="${(data.taskLabelName)!}" placeholder="任务标签">
                </div>
                <div class="col-md-2">
                    <select name="status" id="status" class="form-control">
                        <option value="-1">任务状态</option>
                        <option value="0" <#if ((data.status)!-2)==0>selected</#if>>未完成</option>
                        <option value="1" <#if ((data.status)!-2)==1>selected</#if>>已完成</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <input name="finishTime" id="finishTime" type="text" class="form-control datepicker" value="<#if (data.finishTime)??>${data.finishTime?date}</#if>" placeholder="完成时间" readonly>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-info" data-page="1" id="fengyulou-search">查询</button>
                    <button type="button" class="btn btn-warning" id="fengyulou-clear">清空</button>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-10">
                    <input name="sketch" id="sketch" type="text" class="form-control" value="${(data.sketch)!}" placeholder="任务简述">
                </div>
            </div>
        </form>
        <div class="fun-btn btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-success" id="fengyulou-insert">添加</button>
            <button type="button" class="btn btn-primary" id="fengyulou-update">修改</button>
            <button type="button" class="btn btn-danger" id="fengyulou-delete">删除</button>
            <button type="button" class="btn btn-success" id="fengyulou-finish">完成</button>
        </div>
        <form id="dataForm"></form>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th width="2%"><input type="checkbox" class="checkall"></th>
                <th width="10%"><strong>项目名称</strong></th>
                <th><strong>任务简述</strong></th>
                <th width="9%"><strong>任务状态</strong></th>
                <th width="9%"><strong>任务标签</strong></th>
                <th width="9%"><strong>完成时间</strong></th>
                <th width="6%"><strong>执行人</strong></th>
            </tr>
            </thead>
            <tbody>
            <#list pageInfo.list as data>
            <tr>
                <td><input type="checkbox" name="ids" value="${data.id}" class="checkbox"></td>
                <td><span class="label label-success">${(data.projectName)!}</span></td>
                <td>${(data.sketch)!}</td>
                <td><#if data.status==0><span class="label label-danger">未完成</span><#else><span class="label label-success">已完成</span></#if></td>
                <td>${(data.taskLabelName)!}</td>
                <td>
                    <#if (data.finishTime)??>
                        ${data.finishTime?date}
                    </#if>
                </td>
                <td><span class="label label-primary">${data.memberName}</span></td>
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
            openPage('/fyl/task/insert')
        })
        // 修改
        $('#fengyulou-update').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            var id = $(".checkbox:checked")[0].value;
            openPage('/fyl/task/update?id=' + id)
        })
        // 删除
        $('#fengyulou-delete').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            delFun('/fyl/task/ajax/delete', $("#dataForm").serialize(), function (data) {
                msgFunCallBack(data.msg,function(){
                    if (data.status == 0) {
                        location.reload()
                    }
                })
            })
        })
        $('#fengyulou-finish').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            ajaxFunParam('/fyl/task/ajax/updateStatus', $("#dataForm").serialize(), function (data) {
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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="${pageTitle}">
    <#include "../common/header-script.ftl">
</head>
<body>
<div class="container-fluid">
    <form class="form-horizontal data-form" id="dataForm">
        <input type="hidden" name="id" id="dataId" value="${(data.id)!}">
        <div class="form-group">
            <label class="col-sm-2 control-label text-danger">项目名称</label>
            <div class="col-sm-3">
                <select class="form-control" id="projectId" name="projectId">
                    <#list projectList as item>
                        <option value="${item.id}"
                                <#if ((data.projectId)!0)==item.id>selected</#if>>${item.name}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-success" id="project-insert">添加</button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label text-danger">主机</label>
            <div class="col-md-5">
                <input type="text" name="host" class="form-control" id="host" value="${(data.host)!}" placeholder="主机">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label text-danger">端口</label>
            <div class="col-md-5">
                <input type="text" name="port" class="form-control" id="port" value="${(data.port)!22}" placeholder="端口">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label text-danger">账号</label>
            <div class="col-md-5">
                <input type="text" name="account" class="form-control" id="host" value="${(data.account)!'root'}" placeholder="账号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label text-danger">密码</label>
            <div class="col-md-5">
                <input type="text" name="password" class="form-control" id="password" value="${(data.password)!}" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary" id="fengyulou-save">提交</button>
                <button type="reset" class="btn btn-primary">重置</button>
                <button type="button" class="btn btn-danger" id="fengyulou-close-refresh">关闭</button>
            </div>
        </div>
    </form>
</div>

<#include "../common/footer-script.ftl">
<script>
    // 添加项目
    $("#project-insert").on("click", function () {
        layer.prompt({title: '添加项目'}, function (pass, index) {
            ajaxFunParam("/fyl/project/ajax/save", {'name': pass}, function (data) {
                if (data.status == 0) {
                    ajaxFunText("/fyl/project/ajax/list", function (data) {
                        $("#projectId").html(data);
                    })
                    layer.close(index);
                }else{
                    msgFun(data.msg)
                }
            })
        });
    })
    // 保存任务
    $("#fengyulou-save").on("click", function () {
        ajaxFunParam("/fyl/computer/ajax/save", $("#dataForm").serialize(), function (data) {
            msgFunCallBack(data.msg,function(){
                if (data.status == 0) {
                    $("#dataId").val(data.data)
                }
            })
        })
    })
</script>
</body>
</html>
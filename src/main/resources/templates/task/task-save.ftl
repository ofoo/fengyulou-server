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
            <label class="col-md-2 control-label text-danger">任务简述</label>
            <div class="col-md-5">
                <textarea name="sketch" class="form-control" rows="3" id="sketch"
                          placeholder="任务简述">${(data.sketch)!}</textarea>
            </div>
        </div>
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
            <label class="col-sm-2 control-label text-danger">任务标签</label>
            <div class="col-sm-3">
                <select class="form-control" id="taskLabelId" name="taskLabelId">
                <#list taskLabelList as item>
                    <option value="${item.id}"
                                <#if ((data.taskLabelId)!0)==item.id>selected</#if>>${item.name}</option>
                </#list>
                </select>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-success" id="taskLabel-insert">添加</button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label text-danger">执行人</label>
            <div class="col-sm-3">
                <select class="form-control" id="memberId" name="memberId">
                <#list memberList as item>
                    <option value="${item.id}"
                                <#if ((data.memberId)!0)==item.id>selected</#if>>${item.name}</option>
                </#list>
                </select>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-success" id="member-insert">添加</button>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label text-danger">任务状态</label>
            <div class="col-sm-5">
                <select class="form-control" id="status" name="status">
                    <option value="0" <#if ((data.status)!0)==0>selected</#if>>未完成</option>
                    <option value="1" <#if ((data.status)!0)==1>selected</#if>>已完成</option>
                </select>
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
                }
            })
        });
    })
    // 添加任务标签
    $("#taskLabel-insert").on("click", function () {
        layer.prompt({title: '添加任务标签'}, function (pass, index) {
            ajaxFunParam("/fyl/taskLabel/ajax/save", {'name': pass}, function (data) {
                if (data.status == 0) {
                    ajaxFunText("/fyl/taskLabel/ajax/list", function (data) {
                        $("#taskLabelId").html(data);
                    })
                    layer.close(index);
                }
            })
        });
    })
    // 添加执行人
    $("#member-insert").on("click", function () {
        openPage("/fyl/member/insert?str=task");
    })
    // 保存任务
    $("#fengyulou-save").on("click", function () {
        ajaxFunParam("/fyl/task/ajax/save", $("#dataForm").serialize(), function (data) {
            msgFunCallBack(data.msg,function(){
                if (data.status == 0) {
                    $("#dataId").val(data.data)
                }
            })
        })
    })

    function initFun(){
        // 刷新人员列表
        ajaxFunText("/fyl/member/ajax/list", function (data) {
            $("#memberId").html(data);
        })
    }
</script>
</body>
</html>
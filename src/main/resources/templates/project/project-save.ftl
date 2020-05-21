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
            <label class="col-md-2 control-label text-danger">项目名称</label>
            <div class="col-md-5">
                <input name="name" type="text" class="form-control" id="name" value="${(data.name)!}"
                       placeholder="请输入项目名称">
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
    // 保存数据
    $("#fengyulou-save").on("click", function () {
        ajaxFunParam("/fyl/project/ajax/save", $("#dataForm").serialize(), function (data) {
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
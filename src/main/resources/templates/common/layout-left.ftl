<div class="admin-left">
    <div class="page-header">
        <h1>风雨楼</h1>
    </div>
    <div class="menu">
        <ul>
            <li>
                <span>任务管理<i class="icon"></i></span>
                <dl>
                    <dt><a href="/fyl/task/list/page">任务列表</a></dt>
                    <dt><a href="/fyl/taskLabel/list/page">任务标签</a></dt>
                </dl>
            </li>
            <li>
                <span>云服务器<i class="icon"></i></span>
                <dl>
                    <dt><a href="/fyl/computer/list/page">机器列表</a></dt>
                </dl>
            </li>
            <li>
                <span>人员管理<i class="icon"></i></span>
                <dl>
                    <dt><a href="/fyl/member/list/page">人员列表</a></dt>
                    <dt><a href="/fyl/memberLabel/list/page">人员标签</a></dt>
                </dl>
            </li>
            <li>
                <span>项目管理<i class="icon"></i></span>
                <dl>
                    <dt><a href="/fyl/project/list/page">项目列表</a></dt>
                </dl>
            </li>
            <#if curUser.type==1>
            <li>
                <span>用户管理<i class="icon"></i></span>
                <dl>
                    <dt><a href="/fyl/user/list/page">用户列表</a></dt>
                </dl>
            </li>
            </#if>
        </ul>
    </div>
</div>
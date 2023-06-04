<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editUserUrl" value="/ajax-admin-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <c:if test="${not empty item.pojo.userId}">
                        <h4 class="modal-title"><fmt:message key="label.user.edit" bundle="${lang}"/></h4>
                    </c:if>
                    <c:if test="${empty item.pojo.userId}">
                        <h4 class="modal-title"><fmt:message key="label.user.add" bundle="${lang}"/></h4>
                    </c:if>
                </div>
                <form action="${editUserUrl}" method="post" id="editUserForm">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.email' bundle='${lang}'/>" class="form-control" value="${item.pojo.email}" id="email" name="pojo.email" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="password" placeholder="<fmt:message key='label.user.password' bundle='${lang}'/>" class="form-control" value="${item.pojo.password}" id="password" name="pojo.password" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.name' bundle='${lang}'/>" class="form-control" value="${item.pojo.name}" id="username" name="pojo.name" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="number" placeholder="<fmt:message key='label.user.age' bundle='${lang}'/>" class="form-control" value="${item.pojo.age}" id="age" name="pojo.age"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.gender' bundle='${lang}'/>" class="form-control" value="${item.pojo.gender}" id="gender" name="pojo.gender"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.address' bundle='${lang}'/>" class="form-control" value="${item.pojo.address}" id="address" name="pojo.address"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.phone' bundle='${lang}'/>" class="form-control" value="${item.pojo.phone}" id="phone" name="pojo.phone"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.userId}">
                                            <select id="role" name="roleId">
                                                <option value="${item.pojo.roleDTO.roleId}">${item.pojo.roleDTO.roleName}</option>
                                                <c:forEach items="${item.roles}" var="itemRole">
                                                    <c:if test="${itemRole.roleId ne item.pojo.roleDTO.roleId}">
                                                        <option value="${itemRole.roleId}">${itemRole.roleName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select id="role" name="roleId">
                                                <option value="-1"><fmt:message key="label.option.role" bundle="${lang}"/></option>
                                                <c:forEach items="${item.roles}" var="itemRole">
                                                    <option value="${itemRole.roleId}">${itemRole.roleName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.isVip' bundle='${lang}'/>" class="form-control" value="${item.pojo.isVip}" id="isVip" name="pojo.isVip"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" placeholder="<fmt:message key='label.user.isActive' bundle='${lang}'/>" class="form-control" value="${item.pojo.isActive}" id="isActive" name="pojo.isActive"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty item.pojo.userId}">
                        <input type="hidden" name ="pojo.userId" value="${item.pojo.userId}"/>
                    </c:if>
                    <input type="hidden" name="crudaction" id="crudactionEdit"/>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="label.close" bundle="${lang}"/></button>
                    <button type="button" id="btnSave" class="btn btn-primary"><fmt:message key="label.save" bundle="${lang}" /></button>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>


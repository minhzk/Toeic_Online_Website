<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.home" bundle="${lang}"/></title>
</head>
<body>
<div class="row">
    <div class="span9">
        <!--Blog Post-->
        <div class="blog-post">
            <h2>${item.pojo.title}</h2>
            <p>${item.pojo.content}</p>
            <a class="btn btn-primary" href="<c:url value="/danh-sach-huong-dan-doc.html"/>"><fmt:message key="label.list.back" bundle="${lang}"/></a>
        </div>
        <!--===============-->
        <div class="row">

            <c:if test="${empty login_name}">
                <form>
                    <fieldset>
                        <h3>Bình luận</h3>

                        <textarea class="input-xxlarge" rows="3"
                                  placeholder="Viết bình luận đánh giá về bài đăng này..." name="comment"
                                  disabled style="text-align: justify;">

					 Đăng nhập để bình luận bài viết
					</textarea>
                        <br>
                        <button type="button" class="btn btn-primary"disabled>Đăng
                            bình luận</button>
                    </fieldset>

                </form>

            </c:if>




            <c:if test="${not empty login_name}">
                <!--  	<input type="hidden" id="user_id" name="user_id" value="${currentUser.id}"/> -->
                <%--                <input type="hidden" id="id_bai_tu_vung" value="${idBaiTuVung}" />--%>

                <div class="blog-spot">
                    <div>
                        <h3>Bình luận</h3>
                        <textarea id="contentComment" class="input-xxlarge" rows="3"
                                  name="contentComment"
                                  placeholder="Viết bình luận đánh giá về bài đăng này..."></textarea>

                    </div>
                    <div>
                        <button id="btnComment" type="button" class="btn btn-primary">Đăng
                            bình luận</button>
                    </div>
                </div>


            </c:if>





            <!-- Nội dung commnetn -->


            <h1 id="testajax"></h1>

            <div id="listcomment">
                <c:forEach items="${listcomment}" var="list">
                    <h4 style="color: dodgerblue;font-style: italic;" id="name_member">${list.user.email}</h4>
                    <h4 style="color: red" id="name_member">${list.createdDate}</h4>
                    <textarea disabled class="input-xxlarge showtext" rows="3"
                              name="cmtvocabularycontent">${list.content}</textarea>
                </c:forEach>
            </div>




            <!-- End Nội dung commnetn -->

        </div>

    </div>
</div>
<script>
    $(document).ready(function() {

        $('#btnComment').click(function() {
            var content = $('#contentComment').val();
            $.ajax({
                url: '/danh-sach-huong-dan-doc.html', // đường dẫn đến controller
                type: 'post', // phương thức HTTP
                data: {
                    content: content,
                    readGuidelineId:${item.pojo.readGuidelineId},
                }, // dữ liệu gửi lên

                success: function(response) {
                    location.reload();
                },
                error: function(xhr) {
                    // xử lý lỗi
                }
            });
        });

    });
</script>
</body>
</html>
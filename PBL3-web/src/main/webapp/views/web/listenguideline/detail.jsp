<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.home" bundle="${lang}"/></title>
    <style>
        #comment-box {
            border: 2px solid #000;
            border-radius: 20px;
            width: 75%;
            padding: 24px;
        }
        .blog-spot {
            border: 1px solid #000;
            padding: 12px;
            border-radius: 20px;
            background-color: #F5F5F5;
        }

    </style>
</head>
<body>
<div class="row">
    <div class="span9">
        <!--Blog Post-->
        <div class="blog-post">
            <h2>${item.pojo.title}</h2>
            <p>${item.pojo.content}</p>
            <a class="btn btn-primary" href="<c:url value="/danh-sach-huong-dan-nghe.html"/>"><fmt:message key="label.list.back" bundle="${lang}"/></a>
        </div>
        <!--===============-->
        <div class="row" id="comment-box">

            <c:if test="${empty login_name}">
                <form>
                    <div class="form-comment blog-spot">
                        <h3>Bình luận</h3>

                        <textarea class="input-xxlarge" rows="3"
                                  placeholder="Viết bình luận đánh giá về bài đăng này..." name="comment"
                                  disabled style="text-align: justify;">

					 Đăng nhập để bình luận bài viết
					</textarea>
                        <br>
                        <button type="button" class="btn btn-primary"disabled>Đăng
                        bình luận</button>
                    </div>

                </form>

            </c:if>




            <c:if test="${not empty login_name}">
                <!--  	<input type="hidden" id="user_id" name="user_id" value="${currentUser.id}"/> -->
<%--                <input type="hidden" id="id_bai_tu_vung" value="${idBaiTuVung}" />--%>

                    <div class="blog-spot" >
                        <div>
                            <h3 style="margin: 0; padding-left: 6px;">Bình luận</h3>
                            <textarea id="contentComment" class="input-xxlarge" rows="3"
                                      name="contentComment"
                                      placeholder="Viết bình luận đánh giá về bài đăng này..."></textarea>
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
                url: '/danh-sach-huong-dan-nghe.html', // đường dẫn đến controller
                type: 'post', // phương thức HTTP
                data: {
                    content: content,
                    listenGuidelineId:${item.pojo.listenGuidelineId},
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
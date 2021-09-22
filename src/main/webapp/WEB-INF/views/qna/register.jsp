<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--헤더 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/header.jsp" %>

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">REGISTER</h1>
                        </div>
                        <form class="user">
                            <div class="form-group">
                                <label for="title">title</label>
                                <input type="text" name="title" class="form-control form-control-user" id="title"
                                       placeholder="title">
                            </div>
                            <label for="title">title</label>
                            <div class="form-group">
                                <input type="text" name="title" class="form-control form-control-user" id="title"
                                       placeholder="title">
                            </div>
                            <div class="form-group">
                                <input type="text" name="title" class="form-control form-control-user" id="title"
                                       placeholder="title">
                            </div>
                            <a href="login.html" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </a>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="forgot-password.html">Forgot Password?</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="login.html">Already have an account? Login!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!--푸터 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/footer.jsp" %>

</body>

</html>
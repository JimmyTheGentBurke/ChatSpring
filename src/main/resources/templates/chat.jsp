<%@ page import="com.pzenterprise.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Pavlican chat</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<main class="content">
    <div class="hamburger-menu">
        <input id="menu__toggle" type="checkbox"/>
        <label class="menu__btn" for="menu__toggle">
            <span></span>
        </label>
        <ul class="menu__box">
            <li><a class="menu__item" href="#drop-window">Create new chat</a></li>
            <li><a class="menu__item" href="#AddNewUser">Add User To Chat</a></li>
        </ul>
    </div>


    <div class="container p-0">
        <h1 class="h3 mb-3">Messages</h1>
        <div></div>
        <div class="card">
            <div class="row g-0 card-content">
                <div class="col-12 col-lg-5 col-xl-3 border-right">
                    <div class="px-4 d-none d-md-block">
                        <div class="d-flex align-items-center">
                            <div class="flex-grow-1 dropdown">
                                <input type="text" class="form-control my-3" placeholder="Search...">
                                <%--Users--%>
                                <div class="dropdown-content">
                                    <c:forEach var="users" items="${requestScope.users}">
                                        <a href="${pageContext.request.contextPath}/chat?userIdCreateSearch=${users.id}#chatFromSearch"
                                           class="list-group-item list-group-item-action border-0">
                                                <%--                                            <div class="badge bg-success float-right">5</div>--%>
                                            <div class="d-flex align-items-start">
                                                <img src="https://bootdey.com/img/Content/avatar/avatar5.png"
                                                     class="rounded-circle mr-1" alt="Vanessa Tucker" width="40"
                                                     height="40">
                                                <div class="flex-grow-1 ml-3">
                                                    <p>User name:<br> ${users.nickName}</p>
<%--                                                    <div class="small"><span class="fas fa-circle chat-online"></span>--%>
<%--                                                        Online--%>
<%--                                                    </div>--%>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                                <%--Users--%>
                            </div>
                        </div>
                    </div>
                    <!-- Chats -->
                    <c:forEach var="chat" items="${requestScope.chats}">
                        <a href="${pageContext.request.contextPath}/message?chatId=${chat.id}"
                           class="list-group-item list-group-item-action border-0">
<%--                            <div class="badge bg-success float-right">5</div>--%>
                            <div class="d-flex align-items-start">
                                <img src="https://png.pngtree.com/png-vector/20191129/ourlarge/pngtree-chat-icon-blue-vector--discussion-icon-png-image_2047530.jpg"
                                     class="rounded-circle mr-1" alt="Vanessa Tucker" width="40" height="40">
                                <div class="flex-grow-1 ml-3">
                                    <p>Chat name:<br> ${chat.name}</p>
<%--                                    <div class="small"><span class="fas fa-circle chat-online"></span> Online</div>--%>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                    <hr class="d-block d-lg-none mt-1 mb-0">
                </div>
                <!-- Chats -->
                <div class="col-12 col-lg-7 col-xl-9">
                    <div class="py-2 px-4 border-bottom d-none d-lg-block">
                        <div class="d-flex align-items-center py-1">
                            <div class="position-relative">
                                <%--                                <img src="https://bootdey.com/img/Content/avatar/avatar3.png"--%>
                                <%--                                     class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">--%>
                            </div>
                            <div class="flex-grow-1 pl-3">
                                <strong>Users in chat : </strong>
                                <c:forEach var="recipients" items="${requestScope.recipients}">
                                <strong>${recipients.nickName} -></strong>
                                </c:forEach>
                                <%--                                <div class="text-muted small"><em>Typing...</em></div>--%>
                            </div>

                            <div>
                                <form action="${pageContext.request.contextPath}/logout" method="post">
                                    <button class="btn btn-primary" type="submit">Logout</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Chats -->
                    <!-- Messages -->
                    <div class="position-relative message-content">
                        <div class="chat-messages p-4 messages-place">
                            <%--                            Drop Window Creation Of Chat P2P--%>
                                <div id="chatFromSearch">
                                    <div id="windowFromSearch">
                                        <form action="${pageContext.request.contextPath}/chat" class="pop-up-window"
                                              method="post">
                                            <label for="chatName">
                                                Chat name: <input id="createChatFromSearch" type="text" name="createChatFromSearch">
                                            </label>
                                            <input type="hidden" name="userIdCreateSearch" value="${requestScope.get("userIdCreateSearch")}">
                                            <br>
                                            <button type="submit" class="btn btn-primary">Create new chat</button>
                                            <a href="#" class="btn btn-primary">Close</a>
                                        </form>
                                    </div>
                                </div>
                            <%--                            Drop Window Creation Of Chat P2P--%>
                                <div id="AddNewUser">
                                    <div id="Adduser">
                                        <form action="${pageContext.request.contextPath}/chat" class="pop-up-window"
                                              method="post">
                                            <label for="chatName">
                                                User name: <input id="UserName" type="text" name="UserName">
                                            </label>
                                            <input type="hidden" name="chatIdForAddUser" value="${requestScope.get('chatId')}">
                                            <button type="submit" class="btn btn-primary">Add User</button>
                                            <a href="#" class="btn btn-primary">Close</a>
                                        </form>
                                    </div>
                                </div>
<%--                                Drop window--%>
                                <div id="drop-window">
                                    <div id="window">
                                        <form action="${pageContext.request.contextPath}/chat" class="pop-up-window"
                                              method="post">
                                            <label for="chatName">
                                                Chat name: <input id="chatName" type="text" name="chatName">
                                            </label>
                                            <br>
                                            <label for="recipientName">
                                                Recipient: <input id="recipientName" type="text" name="recipientName">
                                            </label>
                                            <br>
                                            <label for="type">Choose chat type:
                                                <select name="type" id="type">
                                                    <option value="p2p">P2P</option>
                                                    <option value="group">Group</option>
                                                </select>
                                            </label>
                                            <button type="submit" class="btn btn-primary">Create new chat</button>
                                        </form>
                                        <br>
                                        <a href="#" class="btn btn-primary">Close</a>
                                    </div>
                                </div>
                            <%--                            Drop Window Creation Of Chat P2P--%>
                            <c:if test="${requestScope.get('messages') != null}">
                                <c:forEach var="message" items="${requestScope.messages}">
                                    <c:if test="${message.creatorId.id == sessionScope.get('user').id}">
                                        <div class="chat-message-right pb-4">
                                            <div>
                                                <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                                     class="rounded-circle mr-1" alt="Chris Wood" width="40"
                                                     height="40">
                                                <div class="text-muted small text-nowrap mt-2">2:33 am</div>
                                            </div>
                                            <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                                <div class="font-weight-bold mb-1">${sessionScope.get('user').nickName}</div>
                                                <p>${message.text} </p>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${message.creatorId.id != sessionScope.get('user').id}">
                                        <div class="chat-message-left pb-4">
                                            <div>
                                                <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                                     class="rounded-circle mr-1" alt="Chris Wood" width="40"
                                                     height="40">
                                                <div class="text-muted small text-nowrap mt-2">2:33 am</div>
                                            </div>
                                            <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                                <div class="font-weight-bold mb-1">${message.creatorId.nickName}</div>
                                                <p>${message.text} </p>
                                            </div>

                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <!-- Message -->
                        </div>
                    </div>
                    <div class="flex-grow-0 py-3 px-4 border-top">
                        <div class="input-group">
                            <form action="${pageContext.request.contextPath}/message?chatId=${requestScope.get('chatId')}"
                                  method="post">
                                <label class="input-label">
                                    <input type="text" name="textMessage" class="form-control"
                                           placeholder="Type your message">
                                </label>
                                <button class="btn btn-primary">Send</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<style>
    <%@include file="css/chatStyle.css" %>
</style>
<script>
    function lastMessageScroll(b) {
        const e = document.querySelector('.chat-messages');
        if (!e) return;

        e.scrollIntoView({
            behavior: b || 'auto',
            block: 'end',
        });
    }
</script>
</body>
</html>
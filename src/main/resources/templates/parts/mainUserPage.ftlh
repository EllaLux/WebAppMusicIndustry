<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Music Industry</title>
    <!-- ссылка на стиль -->
    <link rel="stylesheet" href="/static/css/style.css">

    <!-- сообщаем браузеру, что он должен считывать плотность пикселей на экране устройства и делать крупнее шрифт и элементы интерфейса (для отображения на разных устройствах)-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body style="background-color: #45464f">
<!-- добавляем панель навигации в шаблон -->
<div class="main">
    <header class="bg-img header">
        <#include "navbar.ftlh">
        <div class="container">
            <div class="row">
                <div class="intro-box">
                    <div class="intro">
                        <#if !isAdmin>
                            <h1>${company.getName()}</h1>
                            <p>Международное музыкальное агентство</p>
                        <#else>
                            <p>Добро пожаловать!</p>
                            <h1>${companyMessage}</h1>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <#if !isAdmin>
        <section id="about" class="about section">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h2 class="title">About company</h2>
                        <p>
                            Международная музыкальная компания ${company.getName()}
                            основана ${company.getDate()}, расположена по адресу ${company.getAddress()}.
                            Бюджет компании ${company.getBudget()} $. Более подробно ознакомиться с
                            работой компании можно по ссылке: <a>${company.getLink().getSite()}</a>.
                        </p>
                        <img class="photoProfile" src="/static/img/company0.png" height="200" width="270">
                    </div>
                </div>
            </div>
        </section>
    </#if>
    <section class="purpose section about">
        <div class="container">
            <div class="row">
                <div class="col">
                    <h2 class="title">What can you do</h2>
                    <div class="row">
                        <div class="col">
                            <div class="vira-card">
                                <div class="vira-card-header">
                                    <div class="card-icon">
                                        <img class="photoProfile" src="/static/img/staff_blue0.png">
                                    </div>
                                </div>
                                <div class="vira-card-content">
                                    <h3><a href="/staff">Staff</a></h3>
                                    <p class="padding">
                                        Информация о сотрудниках компании
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="vira-card">
                                <div class="vira-card-header">
                                    <div class="card-icon">
                                        <img class="photoProfile" src="/static/img/artist_blue0.png">
                                    </div>
                                </div>
                                <div class="vira-card-content">
                                    <h3><a href="/artist">Artist</a></h3>
                                    <p class="padding">
                                        Информация об артистах компании
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="vira-card">
                                <div class="vira-card-header">
                                    <div class="card-icon">
                                        <img class="photoProfile" src="/static/img/activity0.png">
                                    </div>
                                </div>
                                <div class="vira-card-content">
                                    <h3><a href="/activity">Activity</a></h3>
                                    <p class="padding">
                                        Карьера артистов. Информация об альбомах, фильмах, концертах и наградах
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <#if isAdmin>
                        <div class="row">
                            <div class="col">
                                <div class="vira-card">
                                    <div class="vira-card-header">
                                        <div class="card-icon">
                                            <img class="photoProfile" src="/static/img/userList0.png">
                                        </div>
                                    </div>
                                    <div class="vira-card-content">
                                        <h3><a href="/user">Users list</a></h3>
                                        <p class="padding">
                                            Информация об уровне доступа пользователей каждой компании
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="vira-card">
                                    <div class="vira-card-header">
                                        <div class="card-icon">
                                            <img class="photoProfile" src="/static/img/link0.png">
                                        </div>
                                    </div>
                                    <div class="vira-card-content">
                                        <h3><a href="/company">Company</a></h3>
                                        <p class="padding">
                                            Информация о всех компаниях
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </section>
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <img src="/static/img/logoPopStarsFooter.png" alt="">
                    <p> Чтобы начать, нужно перестать говорить и начать делать</p>
                </div>
            </div>
        </div>
    </footer>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
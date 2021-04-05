1. [Цель проекта](#introduction)
2. [Полное описание проекта](#introduction2)


Цель проекта: <a name="introduction"></a>
 - Человек производит авторизацию на сайте заходит его просят добавить свои желания, например, он добавляет несколько своих желаний, которые он хочет, чтобы произошли, его просят отметить желание, которое хотелось бы исполнить в реальность ему приходит сообщение ваше желание обрабатывается мы передали его менеджеру, заявка приходит менеджеру. Например, заявка содержит такое содержание, я хотел бы совершить прыжок с парашюта, но все время не решался я хочу, чтобы мое желание сбылось.
Менеджер приступает поиску по городу, компаний, которые производят данные услуги, и не посредственно передает информацию человека, когда можно будет записаться на прыжок с парашюта и стоимость данной услуги для человека.


  Backend часть:
 1)	этап 
  - Создание Spring Boot приложения(веб-сайт), конфигурация нашего проекта добавление необходимых зависимостей: 
https://github.com/Dmitrii-Kolikov/web_application_wishlist/blob/main/pom.xml
 2) этап
 - Настройка application.properties: 
https://github.com/Dmitrii-Kolikov/web_application_wishlist/blob/main/src/main/resources/application.properties
 - Подключение к БД MySQL
 3) этап
 - Создание Model: Wishlist, User(@ManyToOne-(Bi-directional));
 - Создание Repository: UserRepository, WishlistRepository; 
 - Создание Service: UserService, WishlistService;
 - Настройка сортировки нашего списка;
 - Создание Controller-а: WishlistController(Добваление, удаление, редатирование, сортировка);
 - Создание Dto: UserDto;
 4) этап
 - Настройка Spring Security;
 - Создание controller-а: LogginController;
 - Насройка SecurityConfig;
 - Насройка UserAuthService;
 5) Этап
 - Создание тестов;
 
 Frontend часть:
 1) Этап 
  - Настройка html страниц с использованием Thymeleaf;
 - Обработка исключений;
 - Настройка дизайна страниц с помощью css и картинок;


 
  Что необходимо иправить и доделать:
1) Переделать сортировку, наших полей;
2) Доделать тесты нашего приложения;
3) Добавить в проект Docker;
4) Размести наш проект на облачный сервер Heroku;
5) Расширить функциональность программы;

_____________________________
  


















































Проект Wishlsitt – который исполняет ваши желания. <a name="introduction2"></a>


#### Список желаний
Цель нашего проекта
- Сделанно список желаний, для каждого юзера будет свой список
- Сделанно фильтр по выполненым желаний (доделать фильтр, для каждого юзера он должен свой)
- Сделанно регистрацию и аутентификацию пользователя, у каждого будет свои личный проект
- Сделано сделать фон из картинок и отредактировать элементы с помощью css.
- Доделать покрыть более детально наше приложение тестами
- Не сделано добавить в проект Docker
- Не сделано добавить в проект на облачный сервисе Heroku

Основные задачи приложения состоит.
1)  Человек заходит на сайт регистрируется или авторизуется
2)  Для каждого человека свой личный кабинет.
3) Человек может добавлять свои желания.
4) Удаление список желаний
5) Редактирование желаний
6) Сортировка желание, которые случились или нет
7) Сортировка по страницам по 5 элементов на странице
8) При переходе на разные формы страниц, разные красивые фоновые фотографии.

Цель нашего проекта, когда он будет сделан:
 - Человек производит авторизацию на сайте заходит его просят добавить свои желания, например он добавляет несколько своих желаний, которые он хочет чтобы произошли, его просят пометить желание, которое хотелось бы исполнить в реальность ему приходит сообщение ваше желание обрабатывается мы передали его менеджеру, заявка приходит менеджеру. Например, заявка содержит такое содержание, я хотел бы совершить прыжок с парашюта, но все время не решался я хочу, чтобы мое желание сбылось.
Менеджер приступает поиску по городу, компаний, которые производят данные услуги, и не посредственно передает информацию человека, когда можно будет записаться на прыжок с парашюта и стоимость для человека.



Непосредственно наше приложение, здесь буду добавлять коротко и содержательно информацию, как будет разрабатываться наш проект.
 
1) Этап
Собираем наш проект.
 Есть два варианта сборки проекта перейти на официальный сайт https://start.spring.io/ или  сделать через Idea Ultimate.
 Будем использовать в нашем проекте Java 11, Maven.
Добавляем необходимые зависимости в наш проект.
- Spring Web, Thymeleaf, Spring Security, Spring Data Jpa, MySQL Driver.
 Добавим в pom.xml зависимость hibernate-validator (будем использовать для обработки наших полей в наших сущностях и Thymeleaf позволяет нам выводить ошибки, при помощи тега: 
 th:if="${#fields.hasErrors()}" th:errors="*{ }"
Thymeleaf 5 поддерживает только контроллеры GetMapping и  PostMapping.

2)  этап
Закомментируем наши зависимости Security, в нашем pom.xml на данном этапе пока они нам не понадобятся.
Приступим к конфигурации application.properties.
- Указываем порт, который мы хотим будем использовать: server.port=8080.
- Необходимо прописать нашу связь с нашей базой данных.
- Также добавим createDatabaseIfNotExist=true, чтобы база автоматически создавалась, Spring Boot будет брат на себя эту задачу.
- Прописываем логин и пароль нашей базе данных
spring.mvc.hiddenmethod.filter.enabled=true  - эта конфигурация позволять использовать удаление наших записей в контроллере. 
spring.jpa.show-sql=true  дает возможность видеть SQL  запросы, которые выполняются в нашей программе под капотом Spring Hibernste.
spring.jpa.hibernate.ddl-auto=update = производить автоматическое обновление наших данных при изменение в нашей программе.
3) Этап 	

Создание контроллера WishlistController:
 Создаем CRUD операцию GetMapping для нашей html страницы index, где у нас будет основной функционал, создаем страницу index, где мы будем создавать начало нашего проекта, на первом этапе думаем, какие будут у нас функциональности на нашей страницы, от этого будем отталкиваться,  в нашей разработки приложения.
 Сделаем таблицу где будет у нас поля номер желания, наше желание, произошло да или нет, и добавить функционал редактирование и удаление, а так же добавим кнопку добавление нашего желание, тут уже есть у нас задачи, от которых можно отталкиваться и делать шаг за шагом.

4) Этап
Создание класса Wishlist определяем важные ему необходимые поля id, wish, happened помещаем в папку model, добавляем необходимые аннотации, методы и конструктор по умолчанию, реализуем в этой части аннотации библиотеки: javax.validation.constraints.
Так же здесь можно было использовать зависимость Lombok, желательно для каждого класса создавать отдельный класс Dto, чтобы мы на прямую не взаимодействовали с базой данных, при создание второго класса мы так сделаем для наглядности, как нужно.

5) Этап
Нужно создать Repository нашего класса, а так же service, чтобы у нас сначала связь происходило service-om, а затем уже repository c нашей базой данных.
Прописываем в нашем коде необходимые методы для нашей программы, сохранение желания, отображение всего списка, редактирование удаление и в последующем уже будем добавлять методы в зависимости от нашего функционала приложения.

6) Этап
 Производим настройку нашего контролера добавляем необходимые CRUD операции для нашего приложения, так же создаем необходимые html страницы, производим настройки html страница добавляем необходимый функционал и обрабатываем сразу ошибки нашего проекта при помощи Thymeleaf и Hibernate-validator.

7) Этап
После того как все настроены, можно сделать сортировку нашей страницы. Сортировка будет происходит по полю happened Случилось наше желание или нет? Так же добавим функционал, чтобы у нас на главной страницы, отображался не весь список желаний, а например 5 элементов, и сделать список который можно листать по страницам влево и вправо.

8) Этап
Как мы добавили основной функционал можно приступать к создание класса User, аналогично создаем наш класс добавляем все необходимые аннотации, здесь уже будем использовать обработку при помощи hibernate, при помощи аннотации @Column зададим, чтобы имя было не пустое и уникальное для нашей регистрации, так же добавим поля id password.

9) Этап
Нам необходимо сделать связь между двумя таблицами, мы хотим чтобы у одного юзера была связь с множествами желания @OneToMany, а в классе Wishlist необходимо указать связь @ManyToOne, так же необходимо отредактировать наши контролеры и указать эту связь при помощи Principal: https://docs.oracle.com/javase/8/docs/api/java/security/Principal.html

10) Этап
 Раскомментируем, наш зависимости в pom.xml Spring Security.
Нам необходимо создать контроллер для нашего Spring Security, где у нас будет происходить авторизация и аутентификация, создаем две страницы html логин и регистрация. После этого необходимо сделать нашу конфигурацию Spring Security у нас она будет без ролей, о Spring Security я разбирал на  https://github.com/Dmitrii-Kolikov/Spring_Boot_Security.

11) Этап
 После конфигурации покрываем наш проект тестами, желательно тесты сразу делать при подготовке нашего проекта и постепенно добавлять, при добавление нового функционала сразу производить тесты на всем проекте, так мы точно убедимся, что наше приложение работает и функционирует без ошибок. 


Наши последующие этапы, которые мы должны будем совершить на нашем проекте.
Приложение запускается работает, но еще есть некоторые нюансы, которые нужно исправить и доработать.

1) Исправление списка наших элементов нужно будет отредактировать его, чтобы была привязка к User_id.
2) Доделать тесты нашего приложения
3) Добавить в наш проект Docker
4) Размести наш проект на облачный сервер Heroku
5) Расширить нашу программу

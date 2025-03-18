ТЗ на разработку приложения для курсов
Содержание

Содержание
Общая информация
Используемые источники
Рекомендуемый стек технологий
1. Экран “Онбординг”
2. Регистрация
3. Вход
4. Меню
5. Главный экран

Общая информация
Названия экранов в техническом задании соответствуют названию экранов в Figma
Данные для экранов, полученные из запросов на сервер и данные, продемонстрированные в Figma могут отличаться
Отрисовка элементов из макета в Figma должна быть идентична
Все шрифты, цвета и иконки можно брать из Figma
Не все экраны из Figma обязательно реализовывать - только согласно текущему ТЗ
На выполнение ТЗ дается 5 дней с момента получения этого файла
Используемые источники
Ссылка на макеты
https://www.figma.com/design/QXrKYWsCf6cwXTj8rDfTMD/%D0%A2%D0%B5%D1%81%D1%82%D0%BE%D0%B2%D0%BE%D0%B5-%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-IT-%D0%BA%D1%83%D1%80%D1%81%D1%8B?node-id=0-1&t=kpw92aQ0tvmWPYYh-1
Ссылка моки JSON API. 
https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download





Рекомендуемый стек технологий
Android:
Kotlin 
Retrofit - библиотека по работе с http. Обязательна к использованию. 
Корутины (но если на RX сможете, тоже отлично)
﻿Flow или LiveData или RX 
﻿﻿Dagger или Koin
﻿﻿MVVM
﻿﻿AdapterDelegates
Верстка обычная на XML
Clean Architecture
Многомодульность (обязательно)
Room (не обязательно, если время будет)
1. Экран “Онбординг”

При первом входе в приложение всегда отображается экран “Онбординг”. 


Экран - хардкод. Отображается только в том случае, если пользователь не входил ранее в приложении. После сброса кэша экран отобразится повторно.

Контейнер с названиями курсов сильно шире экрана, поэтому можно создать возможность свайпать эту область влево-вправо. Но это опционально, делать при желании/возможности!


По клику на кнопку “Продолжить” происходит переход на экран регистрации.

2. Регистрация
Этот экран делать не нужно. 
3. Вход

В поле “Email” вводится email по маске “текст@текст.текст”. Для ввода недоступна кириллица.
В поле “Пароль” вводится пароль, для ввода доступны любые символы.
Если email введен не по маске или одно из полей не заполнено, то кнопка входа неактивна. Если все заполнено корректно, то осуществляется переход на Главный экран.

Кнопки “Регистрация” и “Забыл пароль” неактивны.
По клику на кнопки ВК и одноклассников осуществляется переход в браузер, соответственно, по ссылкам https://vk.com/ и https://ok.ru/
4. Меню
После входа в приложение, независимо от текущего экрана, внизу всегда отображается меню с соответствующими иконками:
Главная - выбрана всегда по умолчанию при входе в приложение
Избранное
Аккаунт - экран заглушка


5. Главный экран
Элемент для поиска и иконка фильтра - нефункциональные элементы, хардкод.
Данные по курсам приходят в ответе API: https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download
Выводятся все курсы, что приходят в ответе API в массиве courses и данные берутся из соотв. объектов. Описание объекта:
id - идентификатор курса
title - заголовок курса
text - описание курса.
price - цена курса
rate - рейтинг курса
startDate - дата начала курса
hasLike - признак, добавлен ли курс в избранное
publishDate - дата публикации курса

Ограничение на отображение описания курса - если текст не умещается в 2 строки, то он обрезается согласно макетам.

По клику на кнопку сортировки происходит сортировка на убыванию по полю publishDate.


Если курс добавлен в избранное (hasLike = true), то флаг у курса имеет зеленую заливку .
Пользователь может добавить курс в избранное - происходит запись локально в БД. Добавленные курсы можно посмотреть в разделе “Избранное”.

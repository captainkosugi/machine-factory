--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8 (Homebrew)
-- Dumped by pg_dump version 16.8 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers VALUES (1, 'ООО "Автопром"', '+79005556677', 'autoprom@example.com');
INSERT INTO public.customers VALUES (2, 'ИП Иванов И.И.', '+79123334455', 'ivanovii@mail.com');
INSERT INTO public.customers VALUES (3, 'ОАО "Техномаш"', '+79007778899', 'tehnomasch@example.com');
INSERT INTO public.customers VALUES (4, 'ИП Никитин А.А', '+79991112233', 'nikitina@mail.ru');
INSERT INTO public.customers VALUES (10, 'ИП Агапов В.В', '+7928583716', 'agapov@ya.ru');


--
-- Data for Name: departments; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.departments VALUES (1, 'Производство');
INSERT INTO public.departments VALUES (2, 'Администрация');
INSERT INTO public.departments VALUES (3, 'Логистика');
INSERT INTO public.departments VALUES (4, 'Склад');


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employees VALUES (1, 'Иванов Сергей Иванович', 'Инженер', 1, 85000.00, '2022-03-15');
INSERT INTO public.employees VALUES (2, 'Петрова Анна Сергеевна', 'Бухгалтер', 2, 78000.00, '2021-11-07');
INSERT INTO public.employees VALUES (3, 'Сидоров Петр Владимирович', 'Менеджер', 3, 90000.00, '2020-06-10');
INSERT INTO public.employees VALUES (4, 'Кузнецова Мария Дмитриевна', 'Кладовщик', 4, 70000.00, '2023-01-20');
INSERT INTO public.employees VALUES (5, 'Семенов Андрей Петрович', 'Инженер', 1, 85000.00, '2024-04-25');
INSERT INTO public.employees VALUES (10, 'Антипов Алексей Анатольевич', 'Кладовщик', 4, 55000.00, '2024-06-15');
INSERT INTO public.employees VALUES (11, 'Сергеев Вячеслав Александорович', 'Инженер', 1, 85000.00, '2025-04-16');
INSERT INTO public.employees VALUES (12, 'Антонов Владимир Николаевич', 'Инженер', 1, 85000.00, '2025-06-10');
INSERT INTO public.employees VALUES (13, 'Лавров Сергей Семенович', 'Кладовщик', 4, 55000.00, '2025-06-12');
INSERT INTO public.employees VALUES (14, 'Филипов Артем Сергеевич', 'Кладовщик', 4, 55000.00, '2025-06-16');
INSERT INTO public.employees VALUES (15, 'Петров Александр Владимирович', 'Инженер', 1, 85000.00, '2025-06-16');
INSERT INTO public.employees VALUES (16, 'Сыров Андрей Вадимович', 'Инженер', 1, 85000.00, '2025-06-15');
INSERT INTO public.employees VALUES (17, 'Тихонов Антон Владимирович', 'Инженер', 1, 85000.00, '2025-06-20');
INSERT INTO public.employees VALUES (18, 'Серегина Анна Васильевна', 'Бухгалтер', 2, 78000.00, '2025-06-21');
INSERT INTO public.employees VALUES (19, 'Медведева Анастасия Владимировна', 'Бухгалтер', 2, 78000.00, '2025-06-22');


--
-- Data for Name: equipment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.equipment VALUES (1, 'Токарный станок ТС-500', 'Станок', 1, 'Активен', '2022-03-15');
INSERT INTO public.equipment VALUES (2, 'Фрезерный станок ФС-300', 'Станок', 1, 'На обслуживании', '2021-11-07');
INSERT INTO public.equipment VALUES (3, 'Конвейерная линия КЛ-100', 'Конвейер', 3, 'Не исправен', '2020-06-10');
INSERT INTO public.equipment VALUES (6, 'Токарный станок ТС-300', 'Станок', 1, 'На обслуживании', '2023-02-04');
INSERT INTO public.equipment VALUES (7, 'Пресс П-300', 'Пресс', 4, 'Активен', '2023-02-06');
INSERT INTO public.equipment VALUES (8, 'Конвейерная линия КЛ-100', 'Конвейер', 3, 'Активен', '2022-10-11');
INSERT INTO public.equipment VALUES (9, 'Пресс П-400', 'Пресс', 4, 'На обслуживании', '2025-03-05');
INSERT INTO public.equipment VALUES (10, 'Токарный станок ТС-1000', 'Станок', 1, 'Активен', '2025-05-30');
INSERT INTO public.equipment VALUES (11, 'Токарный станок ТС-1100', 'Станок', 1, 'Активен', '2025-06-15');


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products VALUES (1, 'Двигатель ДВС-300', 'Двигатель', 500000.00, 10, DEFAULT);
INSERT INTO public.products VALUES (2, 'Двигатель ДВС-500', 'Двигатель', 750000.00, 5, DEFAULT);
INSERT INTO public.products VALUES (3, 'Коробка передач КП-200', 'Трансмиссия', 250000.00, 15, DEFAULT);
INSERT INTO public.products VALUES (6, 'Амортизатор АМ-50', 'Ходовая часть', 30000.00, 50, DEFAULT);
INSERT INTO public.products VALUES (7, 'Генератор Г-200', 'Электрическая система', 80000.00, 30, DEFAULT);
INSERT INTO public.products VALUES (8, 'Аккумулятор АКБ-100', 'Электрическая система', 12000.00, 70, DEFAULT);
INSERT INTO public.products VALUES (15, 'Двигатель ДВС-400', 'Двигатель', 100000.00, 0, DEFAULT);
INSERT INTO public.products VALUES (16, 'Выхлопная труба ВТ-50', 'Система выхлопа', 20000.00, 15, DEFAULT);
INSERT INTO public.products VALUES (17, 'Двигатель ДВС-600', 'Двигатель', 900000.00, 2, DEFAULT);
INSERT INTO public.products VALUES (18, 'Подвеска П-200', 'Ходовая часть', 200000.00, 5, DEFAULT);
INSERT INTO public.products VALUES (4, 'Редуктор РЦД-350', 'Трансмиссия', 450000.00, 0, DEFAULT);
INSERT INTO public.products VALUES (9, 'Радиатор РД-150', 'Система охлаждения', 50000.00, 0, DEFAULT);
INSERT INTO public.products VALUES (10, 'Водяной насос ВН-50', 'Система охлаждения', 20000.00, 40, DEFAULT);
INSERT INTO public.products VALUES (11, 'Глушитель ГЛ-100', 'Система выхлопа', 35000.00, 12, DEFAULT);
INSERT INTO public.products VALUES (12, 'Выхлопная труба ВТ-20', 'Система выхлопа', 15000.00, 30, DEFAULT);
INSERT INTO public.products VALUES (13, 'Капот КП-50', 'Кузов', 10000.00, 60, DEFAULT);
INSERT INTO public.products VALUES (14, 'Дверь передняя ДП-30', 'Кузов', 8000.00, 80, DEFAULT);


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.suppliers VALUES (1, 'ООО "Металлторг"', '+79001234567', 'info@metalltorg.ru');
INSERT INTO public.suppliers VALUES (2, 'ООО "Промтехника"', '+79009876543', 'promtech@example.com');
INSERT INTO public.suppliers VALUES (3, 'ИП Сидоров П.В.', '+79123456789', 'sidorovpv@mail.com');
INSERT INTO public.suppliers VALUES (4, 'ООО "Металлпром"', '+7998887766', 'metalprom@yandex.com');
INSERT INTO public.suppliers VALUES (5, 'ИП Андреев А.А', '+7997775544', 'ivanovaa@mail.com');


--
-- Data for Name: goods_movement; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.goods_movement VALUES (10, 4, 1, 'Поставка', 2.000, '2023-03-21', true);
INSERT INTO public.goods_movement VALUES (11, 4, 1, 'Поставка', 4.000, '2023-03-22', true);
INSERT INTO public.goods_movement VALUES (12, 4, 1, 'Реализация', 6.000, '2023-03-24', true);
INSERT INTO public.goods_movement VALUES (15, 4, 2, 'Поставка', 1.000, '2024-02-14', true);
INSERT INTO public.goods_movement VALUES (16, 4, 2, 'Реализация', 1.000, '2024-02-15', true);
INSERT INTO public.goods_movement VALUES (18, 8, 1, 'Реализация', 37.000, '2025-01-24', true);
INSERT INTO public.goods_movement VALUES (19, 8, 3, 'Поставка', 7.000, '2024-06-24', true);
INSERT INTO public.goods_movement VALUES (20, 4, 2, 'Поставка', 1.000, '2025-05-31', true);
INSERT INTO public.goods_movement VALUES (21, 4, 3, 'Реализация', 1.000, '2025-06-01', true);
INSERT INTO public.goods_movement VALUES (23, 17, 1, 'Поставка', 1.000, '2025-06-06', true);
INSERT INTO public.goods_movement VALUES (24, 9, 4, 'Реализация', 25.000, '2025-06-07', true);
INSERT INTO public.goods_movement VALUES (25, 17, 1, 'Реализация', 1.000, '2025-06-15', true);
INSERT INTO public.goods_movement VALUES (26, 17, 4, 'Поставка', 5.000, '2025-06-16', true);
INSERT INTO public.goods_movement VALUES (27, 18, 2, 'Реализация', 5.000, '2025-06-10', true);
INSERT INTO public.goods_movement VALUES (28, 17, 4, 'Реализация', 5.000, '2025-06-20', true);
INSERT INTO public.goods_movement VALUES (30, 17, 4, 'Поставка', 2.000, '2025-06-21', true);


--
-- Name: customers_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_customer_id_seq', 11, true);


--
-- Name: departments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.departments_id_seq', 4, true);


--
-- Name: employees_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_employee_id_seq', 19, true);


--
-- Name: equipment_equipment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipment_equipment_id_seq', 11, true);


--
-- Name: goods_movement_movement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.goods_movement_movement_id_seq', 30, true);


--
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_product_id_seq', 18, true);


--
-- Name: suppliers_supplier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.suppliers_supplier_id_seq', 9, true);


--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-24 01:03:47

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3493 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 238 (class 1259 OID 17157)
-- Name: Category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Category" (
    id integer NOT NULL,
    "Title" integer
);


ALTER TABLE public."Category" OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 17156)
-- Name: Category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Category_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Category_id_seq" OWNER TO postgres;

--
-- TOC entry 3495 (class 0 OID 0)
-- Dependencies: 237
-- Name: Category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Category_id_seq" OWNED BY public."Category".id;


--
-- TOC entry 218 (class 1259 OID 17075)
-- Name: CookStep; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."CookStep" (
    id integer NOT NULL,
    "id_Recipe" integer,
    step_num integer,
    step_text character varying,
    step_image character varying
);


ALTER TABLE public."CookStep" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17074)
-- Name: CookStep_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."CookStep_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."CookStep_id_seq" OWNER TO postgres;

--
-- TOC entry 3498 (class 0 OID 0)
-- Dependencies: 217
-- Name: CookStep_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."CookStep_id_seq" OWNED BY public."CookStep".id;


--
-- TOC entry 236 (class 1259 OID 17150)
-- Name: MeasureType; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."MeasureType" (
    id integer NOT NULL,
    title text
);


ALTER TABLE public."MeasureType" OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 17149)
-- Name: MeasureType_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."MeasureType_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."MeasureType_id_seq" OWNER TO postgres;

--
-- TOC entry 3501 (class 0 OID 0)
-- Dependencies: 235
-- Name: MeasureType_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."MeasureType_id_seq" OWNED BY public."MeasureType".id;


--
-- TOC entry 220 (class 1259 OID 17084)
-- Name: Product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Product" (
    id integer NOT NULL,
    "id_productType" integer,
    "Title" character varying
);


ALTER TABLE public."Product" OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 17123)
-- Name: ProductType; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ProductType" (
    id integer NOT NULL,
    "Title" character varying
);


ALTER TABLE public."ProductType" OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 17122)
-- Name: ProductType_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."ProductType_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."ProductType_id_seq" OWNER TO postgres;

--
-- TOC entry 3505 (class 0 OID 0)
-- Dependencies: 229
-- Name: ProductType_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."ProductType_id_seq" OWNED BY public."ProductType".id;


--
-- TOC entry 219 (class 1259 OID 17083)
-- Name: Product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Product_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Product_id_seq" OWNER TO postgres;

--
-- TOC entry 3507 (class 0 OID 0)
-- Dependencies: 219
-- Name: Product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Product_id_seq" OWNED BY public."Product".id;


--
-- TOC entry 216 (class 1259 OID 17066)
-- Name: Recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Recipe" (
    id integer NOT NULL,
    title character varying,
    cooking_time time without time zone,
    kkal integer,
    "id_CreatorUser" integer,
    image_url character varying
);


ALTER TABLE public."Recipe" OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 17164)
-- Name: RecipeCategory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."RecipeCategory" (
    id integer NOT NULL,
    "id_Recipe" integer,
    "id_Category" integer
);


ALTER TABLE public."RecipeCategory" OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 17163)
-- Name: RecipeCategory_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."RecipeCategory_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."RecipeCategory_id_seq" OWNER TO postgres;

--
-- TOC entry 3511 (class 0 OID 0)
-- Dependencies: 239
-- Name: RecipeCategory_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."RecipeCategory_id_seq" OWNED BY public."RecipeCategory".id;


--
-- TOC entry 224 (class 1259 OID 17102)
-- Name: RecipeIngredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."RecipeIngredient" (
    id integer NOT NULL,
    "id_Product" integer,
    "id_Recipe" integer,
    "id_MeasureType" integer,
    count integer
);


ALTER TABLE public."RecipeIngredient" OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17101)
-- Name: RecipeIngredient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."RecipeIngredient_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."RecipeIngredient_id_seq" OWNER TO postgres;

--
-- TOC entry 3514 (class 0 OID 0)
-- Dependencies: 223
-- Name: RecipeIngredient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."RecipeIngredient_id_seq" OWNED BY public."RecipeIngredient".id;


--
-- TOC entry 215 (class 1259 OID 17065)
-- Name: Recipe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Recipe_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Recipe_id_seq" OWNER TO postgres;

--
-- TOC entry 3516 (class 0 OID 0)
-- Dependencies: 215
-- Name: Recipe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Recipe_id_seq" OWNED BY public."Recipe".id;


--
-- TOC entry 222 (class 1259 OID 17093)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    id integer NOT NULL,
    "id_userRole" integer,
    name character varying,
    region character varying,
    birth_date date,
    created_date timestamp without time zone
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17132)
-- Name: UserCredential; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."UserCredential" (
    id integer NOT NULL,
    "Login" character varying,
    "Password" character varying,
    email character varying,
    "refreshToken" character varying,
    "refreshTokenDate" timestamp without time zone
);


ALTER TABLE public."UserCredential" OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 17131)
-- Name: UserCredential_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."UserCredential_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."UserCredential_id_seq" OWNER TO postgres;

--
-- TOC entry 3520 (class 0 OID 0)
-- Dependencies: 231
-- Name: UserCredential_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."UserCredential_id_seq" OWNED BY public."UserCredential".id;


--
-- TOC entry 228 (class 1259 OID 17116)
-- Name: UserProduct; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."UserProduct" (
    id integer NOT NULL,
    "id_User" integer,
    "id_Product" integer
);


ALTER TABLE public."UserProduct" OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17115)
-- Name: UserProduct_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."UserProduct_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."UserProduct_id_seq" OWNER TO postgres;

--
-- TOC entry 3523 (class 0 OID 0)
-- Dependencies: 227
-- Name: UserProduct_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."UserProduct_id_seq" OWNED BY public."UserProduct".id;


--
-- TOC entry 226 (class 1259 OID 17109)
-- Name: UserRecipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."UserRecipe" (
    id integer NOT NULL,
    "id_User" integer,
    "id_Recipe" integer
);


ALTER TABLE public."UserRecipe" OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17108)
-- Name: UserRecipe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."UserRecipe_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."UserRecipe_id_seq" OWNER TO postgres;

--
-- TOC entry 3526 (class 0 OID 0)
-- Dependencies: 225
-- Name: UserRecipe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."UserRecipe_id_seq" OWNED BY public."UserRecipe".id;


--
-- TOC entry 234 (class 1259 OID 17141)
-- Name: UserRole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."UserRole" (
    id integer NOT NULL,
    title character varying
);


ALTER TABLE public."UserRole" OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 17140)
-- Name: UserRole_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."UserRole_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."UserRole_id_seq" OWNER TO postgres;

--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 233
-- Name: UserRole_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."UserRole_id_seq" OWNED BY public."UserRole".id;


--
-- TOC entry 221 (class 1259 OID 17092)
-- Name: User_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."User_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."User_id_seq" OWNER TO postgres;

--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 221
-- Name: User_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."User_id_seq" OWNED BY public."User".id;


--
-- TOC entry 3277 (class 2604 OID 17160)
-- Name: Category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Category" ALTER COLUMN id SET DEFAULT nextval('public."Category_id_seq"'::regclass);


--
-- TOC entry 3267 (class 2604 OID 17078)
-- Name: CookStep id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CookStep" ALTER COLUMN id SET DEFAULT nextval('public."CookStep_id_seq"'::regclass);


--
-- TOC entry 3276 (class 2604 OID 17153)
-- Name: MeasureType id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."MeasureType" ALTER COLUMN id SET DEFAULT nextval('public."MeasureType_id_seq"'::regclass);


--
-- TOC entry 3268 (class 2604 OID 17087)
-- Name: Product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Product" ALTER COLUMN id SET DEFAULT nextval('public."Product_id_seq"'::regclass);


--
-- TOC entry 3273 (class 2604 OID 17126)
-- Name: ProductType id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ProductType" ALTER COLUMN id SET DEFAULT nextval('public."ProductType_id_seq"'::regclass);


--
-- TOC entry 3266 (class 2604 OID 17069)
-- Name: Recipe id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Recipe" ALTER COLUMN id SET DEFAULT nextval('public."Recipe_id_seq"'::regclass);


--
-- TOC entry 3278 (class 2604 OID 17167)
-- Name: RecipeCategory id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeCategory" ALTER COLUMN id SET DEFAULT nextval('public."RecipeCategory_id_seq"'::regclass);


--
-- TOC entry 3270 (class 2604 OID 17105)
-- Name: RecipeIngredient id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeIngredient" ALTER COLUMN id SET DEFAULT nextval('public."RecipeIngredient_id_seq"'::regclass);


--
-- TOC entry 3269 (class 2604 OID 17096)
-- Name: User id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User" ALTER COLUMN id SET DEFAULT nextval('public."User_id_seq"'::regclass);


--
-- TOC entry 3274 (class 2604 OID 17135)
-- Name: UserCredential id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserCredential" ALTER COLUMN id SET DEFAULT nextval('public."UserCredential_id_seq"'::regclass);


--
-- TOC entry 3272 (class 2604 OID 17119)
-- Name: UserProduct id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserProduct" ALTER COLUMN id SET DEFAULT nextval('public."UserProduct_id_seq"'::regclass);


--
-- TOC entry 3271 (class 2604 OID 17112)
-- Name: UserRecipe id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRecipe" ALTER COLUMN id SET DEFAULT nextval('public."UserRecipe_id_seq"'::regclass);


--
-- TOC entry 3275 (class 2604 OID 17144)
-- Name: UserRole id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRole" ALTER COLUMN id SET DEFAULT nextval('public."UserRole_id_seq"'::regclass);


--
-- TOC entry 3485 (class 0 OID 17157)
-- Dependencies: 238
-- Data for Name: Category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Category" (id, "Title") FROM stdin;
\.


--
-- TOC entry 3465 (class 0 OID 17075)
-- Dependencies: 218
-- Data for Name: CookStep; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."CookStep" (id, "id_Recipe", step_num, step_text, step_image) FROM stdin;
15	9	8	Тесто подошло.	https://img1.russianfood.com/dycontent/images_upl/362/big_361483.jpg
42	13	3	В муку добавить соль и сахар.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_3.webp
43	13	4	Сделать углубление в муке, влить оливковое масло и постепенно ввести воду.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_4.webp
44	13	5	Замесить тесто, накрыть пищевой пленкой и убрать в теплое место на 1 час.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_5.webp
45	13	6	Колбасу нарезать небольшими кусочками.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_6.webp
46	13	7	Сыр натереть на крупной терке.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_7.webp
47	13	8	Помидор нарезать тонкими кружочками.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_8.webp
48	13	9	Застелить пергаментной бумагой противень и силиконовой кистью смазать немного подсолнечным маслом.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_9.webp
49	13	10	Раскатать тесто по форме противня, сформировать небольшие бортики и смазать томатным соусом.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_10.webp
50	13	11	Выложить слоями колбасу и кусочки помидора.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_11.webp
51	13	12	Обильно посыпать сыром. Разогреть духовку до 200 градусов и выпекать 25 минут до готовности.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_12.webp
8	9	1	Подготовить ингредиенты.	https://img1.russianfood.com/dycontent/images_upl/362/big_361610.jpg
9	9	2	Приготовить опару для теста. Для этого в чашке смешать дрожжи, сахар, соль и тёплую воду (от горячей дрожжи погибнут). Перемешать.	https://img1.russianfood.com/dycontent/images_upl/362/big_361611.jpg
10	9	3	Добавить к полученной смеси примерно 5 ст. ложек муки (муку всегда просеиваем). Накрыть чашку салфеткой и оставить в тёплом месте минут на 15-20. Опара увеличится в объеме и покроется пузырьками.	https://img1.russianfood.com/dycontent/images_upl/362/big_361481.jpg
11	9	4	Когда опара подойдёт, добавить растительное масло и оставшуюся муку.	https://img1.russianfood.com/dycontent/images_upl/362/big_361482.jpg
12	9	5	Перемешать и замесить тесто. Дрожжевое тесто для жареных беляшей не должно быть слишком крутым, поэтому муки больше не добавляем. Тесто будет мягким и немного липнуть к рукам.	https://img1.russianfood.com/dycontent/images_upl/362/big_361614.jpg
13	9	6	Чистую сухую чашку и руки смазать растительным маслом. Сформировать из теста шар и выложить в чашку. Накрыть чашку с тестом плёнкой или убрать в пакет и поставить в тёплое место для подъёма, примерно на 1 час.	https://img1.russianfood.com/dycontent/images_upl/362/big_361615.jpg
14	9	7	Приготовить начинку для беляшей. Очищенный лук измельчить в блендере или мелко порезать. В чашке смешать фарш, лук, чёрный свежемолотый перец, соль и немного холодной воды (тогда беляши будут сочные и мясо хорошо пропарится!). Перемешать.	https://img1.russianfood.com/dycontent/images_upl/362/big_361484.jpg
16	9	9	Чтобы тесто не липло, смазать руки и рабочую поверхность растительным маслом. Тесто слегка обмять, выложить на рабочую поверхность. Разделить тесто на равные части (примерно 14), скатать шарики. Дать шарикам теста немного подойти, минут 5-10.	https://img1.russianfood.com/dycontent/images_upl/362/big_361485.jpg
17	9	10	Каждый шарик теста подушечками пальцев аккуратно размять в лепёшку (слишком тонко не разминаем, чтобы тесто не сгорело, а мясо отлично прожарилось!). В центр лепёшек выложить начинку (примерно по одной столовой ложке).	https://img1.russianfood.com/dycontent/images_upl/362/big_361486.jpg
18	9	11	Защипнуть края теста, соединить их в центре.	https://img1.russianfood.com/dycontent/images_upl/362/big_361616.jpg
19	9	12	Слегка придавить ладонью, формируя круглый пирожок. Выложить беляши швом вниз на смазанную маслом поверхность.	https://img1.russianfood.com/dycontent/images_upl/362/big_361617.jpg
20	9	13	В кастрюле или глубокой сковороде разогреть растительное масло (для фритюра). Выложить беляши швом вниз и жарить на среднем огне до золотистого цвета, примерно по 5 минут с каждой стороны.	https://img1.russianfood.com/dycontent/images_upl/362/big_361487.jpg
21	9	14	Горячие беляши достать шумовкой на бумажное полотенце, чтобы избавиться от лишнего масла.	https://img1.russianfood.com/dycontent/images_upl/362/big_361488.jpg
22	9	15	Сочные и воздушные, как пух, беляши с мясом готовы! Приятного Вам аппетита!	https://img1.russianfood.com/dycontent/images_upl/362/big_361705.jpg
23	10	1	Приготовить тесто для русских пельменей. Муку просеять горкой. Сделать сверху углубление, влить в него яйцо и 1 ст. л. воды, добавить щепотку соли.	https://images.gastronom.ru/CRiHT5m58NvB7R0it_ab4BTXTxnY5P4lZZ08_et5Hfs/pr:recipe-step-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzJlODlmMWMyLWYxNTQtNDhmZi04Mzg0LTQ4YzMyODMwOTJmYS5qcGc
24	10	2	Собирая муку с краев к середине, чтобы вода и яйцо не выливались из углубления, замешивать тесто, добавляя небольшими порциями оставшуюся воду. Месить тесто для пельменей до тех пор, пока оно не станет эластичным и однородным, примерно 10 мин. Накрыть влажным полотенцем и оставить на 30 мин. Вода для пельменного теста должна быть ледяная. Для этого ее заранее ставят в холодильник или морозильник.	https://images.gastronom.ru/ohWo5Uvx2IxBKRIF97Yu0GsFPgWlYqkQNnAL2DPwEfc/pr:recipe-step-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzVlNDQ2NWRmLTc0M2ItNDY5Yy1hMTBjLTBlNzVlZDkxMjhhNC5qcGc
25	10	3	Пока расстаивается тесто, приготовить начинку для пельменей. Лук и чеснок очистить и очень мелко порубить. Смешать говяжий и свиной фарш, добавить лук с чесноком, посолить, поперчить. Тщательно перемешать до однородности.	https://images.gastronom.ru/5uVUTG0ZNo6QknWy3RBjHA7YJN8fyJNNUrLmtJA8sc0/pr:recipe-step-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2Y4MDYzZGVjLTAwN2QtNGFlZi1hNDJkLTdjOGZmYTI3ZjdlZi5qcGc
26	10	4	Готовое тесто для русских пельменей разделить на 4 части. 3 части накрыть влажным полотенцем и отложить. Оставшееся тесто скатать в жгут толщиной 2 см. Нарезать его на кусочки шириной 1,5 см. На присыпанной мукой поверхности раскатать каждый кусочек теста в тонкую лепешку.	https://images.gastronom.ru/MlyattIIts6LbvtaJr30k57DYb4viPLdsq2NAuCzV7s/pr:recipe-step-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzFiMDY1NjA2LWU2NzctNGRkOC04NzEyLTMyOWNhMWIwNDFlYy5qcGc
27	10	5	Выложить в центр каждой лепешки по 1,5 ч. л. начинки, сложить кружок с начинкой пополам так, чтобы получился полумесяц. Соединить концы полумесяца и скрепить их. Плотно прижать пальцами, чтобы пельмени не разваливались при варке.	https://images.gastronom.ru/c3V_xqZQVWTAypfUkLfCVZsbMQgXZPgv1OZFTz9Wwys/pr:recipe-step-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2VhZWY5YTkxLTE0OWMtNDgzZS05ZjNmLTBlNjFjNmE4OTgxYi5qcGc
28	10	6	Выложить пельмени на поднос или плоское блюдо, присыпать мукой. Так же приготовить пельмени из оставшегося теста.	https://images.gastronom.ru/PEvY6lm6DtTWna-mYPyfMYwthO2YQyb2QPt1Tx0AFRQ/pr:recipe-step-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzL2Q2ODczZTQ2LTgwMmMtNGI5NC1iOGYyLTNlOWUxZGI4MDkzMy5qcGc
29	11	1	Налить в подходящую емкость молоко комнатной температуры, вбить туда яйца, добавить соль и сахар.	https://eda.ru/images/RecipeStep/434x295/vozdushnie-tonkie-blini-na-moloke_36589_step_1.webp
30	11	2	Постепенно подсыпать муку, при этом помешивая, чтобы не получалось комочков. Они все равно будут получаться, так что помешивать надо качественно. Довести до консистенции нежирной сметаны. Добавить разрыхлитель.	https://eda.ru/images/RecipeStep/434x295/vozdushnie-tonkie-blini-na-moloke_36589_step_2.webp
31	11	3	Все размешать, оставить на 15–20 минут и потом добавить растительное масло. Кстати, тесто для блинов можно оставить в холодильнике и приготовить блины позже. С ним ничего не случится.	https://eda.ru/images/RecipeStep/434x295/vozdushnie-tonkie-blini-na-moloke_36589_step_3.webp
32	11	4	На сильно раскаленную сковороду налить немного масла и жарить блины.	https://eda.ru/images/RecipeStep/434x295/vozdushnie-tonkie-blini-na-moloke_36589_step_4.webp
33	12	1	Спагетти варить 7-10 минут в кипящей подсоленной воде и откинуть на дуршлаг.	\N
34	12	2	В сковороде разогрейте оливковое масло, положите чеснок и слегка подрумяньте.	\N
35	12	3	Ветчину/бекон мелко нарежьте, добавьте к чесноку и обжаривайте 5 минут.	\N
36	12	4	Сыр пармезан натрите на мелкой терке. Желтки взбить со сливками, немного подсолить.	\N
37	12	5	Спагетти переложить в сотейник с чесноком и ветчиной/беконом.	\N
38	12	6	Добавить взбитые желтки и тёртый сыр, перемешать. Держать на огне 3 минуты.	\N
39	12	7	Посыпать молотым перцем, украсить зеленью и подавать на стол.	\N
40	13	1	Пшеничную муку просеять.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_1.webp
41	13	2	Добавить сухие дрожжи. Перемешать.	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_2.webp
52	13	13	Готовую пиццу разделить на порционные кусочки. Приятного аппетита!	https://eda.ru/images/RecipeStep/434x295/picca-domashnyaya_173877_step_13.webp
\.


--
-- TOC entry 3483 (class 0 OID 17150)
-- Dependencies: 236
-- Data for Name: MeasureType; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."MeasureType" (id, title) FROM stdin;
1	мл.
2	гр.
3	кг.
4	шт.
5	ст.л.
6	По вкусу
\.


--
-- TOC entry 3467 (class 0 OID 17084)
-- Dependencies: 220
-- Data for Name: Product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Product" (id, "id_productType", "Title") FROM stdin;
1	\N	Молоко
2	\N	Мясо
3	\N	Сливки
4	\N	Сыр
5	\N	Колбаса
6	\N	Хлеб
7	\N	Вино
9	\N	Ветчина
10	\N	Укроп
11	\N	Помидор
12	\N	Кукуруза
13	\N	Яблоко
14	\N	Яйца
15	\N	Огурец
16	1	Фарш свиной
17	1	Лук репчатый
8	\N	Масло растительное
19	1	Мука пшеничная
18	1	Дрожжи сухие \r\nбыстродействующие
20	1	Фарш говяжий
21	1	Чеснок
22	1	Разрыхлитель
23	1	спагетти
24	1	Масло оливковое
25	1	Томатный соус
\.


--
-- TOC entry 3477 (class 0 OID 17123)
-- Dependencies: 230
-- Data for Name: ProductType; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."ProductType" (id, "Title") FROM stdin;
1	base
\.


--
-- TOC entry 3463 (class 0 OID 17066)
-- Dependencies: 216
-- Data for Name: Recipe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Recipe" (id, title, cooking_time, kkal, "id_CreatorUser", image_url) FROM stdin;
10	Русские пельмени	01:00:00	870	1	https://images.gastronom.ru/-VHKrpGZIuRHlT5J7fHLklcGsVvMDaPqVr778-j3O2k/pr:recipe-cover-image/g:ce/rs:auto:0:0:0/L2Ntcy9hbGwtaW1hZ2VzLzI4NDA1OTE2LTM4ZDMtNDYwMC1hMjYyLTc1NzFkNjc3MDdlZi5qcGc
11	Блины на молоке	00:40:00	149	1	https://img1.russianfood.com/dycontent/images_upl/457/big_456172.jpg
12	Паста карбонара	00:30:00	1375	1	https://eda.ru/images/RecipePhoto/620x415/pasta-karbonara-pasta-alla-carbonara_50865_photo_56238.webp
13	Пицца домашняя	02:00:00	1047	1	https://eda.ru/images/RecipePhoto/620x415/picca-domashnyaya_173877_photo_181239.webp
9	Беляши с мясом	01:30:00	272	1	https://e1.edimdoma.ru/data/recipes/0015/0339/150339-ed4_wide.jpg?1660579420
\.


--
-- TOC entry 3487 (class 0 OID 17164)
-- Dependencies: 240
-- Data for Name: RecipeCategory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."RecipeCategory" (id, "id_Recipe", "id_Category") FROM stdin;
\.


--
-- TOC entry 3471 (class 0 OID 17102)
-- Dependencies: 224
-- Data for Name: RecipeIngredient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."RecipeIngredient" (id, "id_Product", "id_Recipe", "id_MeasureType", count) FROM stdin;
13	8	9	1	300
14	16	9	2	300
15	17	9	4	1
16	18	9	2	7
17	19	9	2	480
18	20	10	2	250
19	16	10	2	250
20	17	10	4	1
21	21	10	4	1
22	14	10	4	1
23	19	10	2	600
24	1	11	2	500
25	14	11	4	2
26	19	11	2	240
27	8	11	5	2
28	22	11	2	1
29	23	12	2	400
30	24	12	5	6
31	21	12	4	2
32	9	12	2	300
33	14	12	4	4
34	4	12	2	100
35	3	12	1	200
36	19	13	2	500
37	18	13	2	10
38	24	13	5	2
39	25	13	6	0
40	5	13	2	300
41	4	13	2	300
42	11	13	4	1
\.


--
-- TOC entry 3469 (class 0 OID 17093)
-- Dependencies: 222
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."User" (id, "id_userRole", name, region, birth_date, created_date) FROM stdin;
1	1	Администратор	Новосибирск	2000-01-01	2024-04-10 15:12:44.409637
\.


--
-- TOC entry 3479 (class 0 OID 17132)
-- Dependencies: 232
-- Data for Name: UserCredential; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."UserCredential" (id, "Login", "Password", email, "refreshToken", "refreshTokenDate") FROM stdin;
\.


--
-- TOC entry 3475 (class 0 OID 17116)
-- Dependencies: 228
-- Data for Name: UserProduct; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."UserProduct" (id, "id_User", "id_Product") FROM stdin;
\.


--
-- TOC entry 3473 (class 0 OID 17109)
-- Dependencies: 226
-- Data for Name: UserRecipe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."UserRecipe" (id, "id_User", "id_Recipe") FROM stdin;
\.


--
-- TOC entry 3481 (class 0 OID 17141)
-- Dependencies: 234
-- Data for Name: UserRole; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."UserRole" (id, title) FROM stdin;
1	Администратор
\.


--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 237
-- Name: Category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Category_id_seq"', 1, false);


--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 217
-- Name: CookStep_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."CookStep_id_seq"', 52, true);


--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 235
-- Name: MeasureType_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."MeasureType_id_seq"', 6, true);


--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 229
-- Name: ProductType_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."ProductType_id_seq"', 1, true);


--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 219
-- Name: Product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Product_id_seq"', 25, true);


--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 239
-- Name: RecipeCategory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."RecipeCategory_id_seq"', 1, false);


--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 223
-- Name: RecipeIngredient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."RecipeIngredient_id_seq"', 42, true);


--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 215
-- Name: Recipe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Recipe_id_seq"', 13, true);


--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 231
-- Name: UserCredential_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."UserCredential_id_seq"', 1, false);


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 227
-- Name: UserProduct_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."UserProduct_id_seq"', 1, false);


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 225
-- Name: UserRecipe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."UserRecipe_id_seq"', 1, false);


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 233
-- Name: UserRole_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."UserRole_id_seq"', 1, true);


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 221
-- Name: User_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."User_id_seq"', 1, true);


--
-- TOC entry 3302 (class 2606 OID 17162)
-- Name: Category Category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Category"
    ADD CONSTRAINT "Category_pkey" PRIMARY KEY (id);


--
-- TOC entry 3282 (class 2606 OID 17082)
-- Name: CookStep CookStep_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CookStep"
    ADD CONSTRAINT "CookStep_pkey" PRIMARY KEY (id);


--
-- TOC entry 3300 (class 2606 OID 17155)
-- Name: MeasureType MeasureType_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."MeasureType"
    ADD CONSTRAINT "MeasureType_pkey" PRIMARY KEY (id);


--
-- TOC entry 3294 (class 2606 OID 17130)
-- Name: ProductType ProductType_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ProductType"
    ADD CONSTRAINT "ProductType_pkey" PRIMARY KEY (id);


--
-- TOC entry 3284 (class 2606 OID 17091)
-- Name: Product Product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY (id);


--
-- TOC entry 3304 (class 2606 OID 17169)
-- Name: RecipeCategory RecipeCategory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeCategory"
    ADD CONSTRAINT "RecipeCategory_pkey" PRIMARY KEY (id);


--
-- TOC entry 3288 (class 2606 OID 17107)
-- Name: RecipeIngredient RecipeIngredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeIngredient"
    ADD CONSTRAINT "RecipeIngredient_pkey" PRIMARY KEY (id);


--
-- TOC entry 3280 (class 2606 OID 17073)
-- Name: Recipe Recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Recipe"
    ADD CONSTRAINT "Recipe_pkey" PRIMARY KEY (id);


--
-- TOC entry 3296 (class 2606 OID 17139)
-- Name: UserCredential UserCredential_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserCredential"
    ADD CONSTRAINT "UserCredential_pkey" PRIMARY KEY (id);


--
-- TOC entry 3292 (class 2606 OID 17121)
-- Name: UserProduct UserProduct_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserProduct"
    ADD CONSTRAINT "UserProduct_pkey" PRIMARY KEY (id);


--
-- TOC entry 3290 (class 2606 OID 17114)
-- Name: UserRecipe UserRecipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRecipe"
    ADD CONSTRAINT "UserRecipe_pkey" PRIMARY KEY (id);


--
-- TOC entry 3298 (class 2606 OID 17148)
-- Name: UserRole UserRole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRole"
    ADD CONSTRAINT "UserRole_pkey" PRIMARY KEY (id);


--
-- TOC entry 3286 (class 2606 OID 17100)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- TOC entry 3306 (class 2606 OID 17175)
-- Name: CookStep CookStep_id_Recipe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CookStep"
    ADD CONSTRAINT "CookStep_id_Recipe_fkey" FOREIGN KEY ("id_Recipe") REFERENCES public."Recipe"(id);


--
-- TOC entry 3307 (class 2606 OID 17180)
-- Name: Product Product_id_productType_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT "Product_id_productType_fkey" FOREIGN KEY ("id_productType") REFERENCES public."ProductType"(id);


--
-- TOC entry 3317 (class 2606 OID 17235)
-- Name: RecipeCategory RecipeCategory_id_Category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeCategory"
    ADD CONSTRAINT "RecipeCategory_id_Category_fkey" FOREIGN KEY ("id_Category") REFERENCES public."Category"(id);


--
-- TOC entry 3318 (class 2606 OID 17230)
-- Name: RecipeCategory RecipeCategory_id_Recipe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeCategory"
    ADD CONSTRAINT "RecipeCategory_id_Recipe_fkey" FOREIGN KEY ("id_Recipe") REFERENCES public."Recipe"(id);


--
-- TOC entry 3309 (class 2606 OID 17200)
-- Name: RecipeIngredient RecipeIngredient_id_MeasureType_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeIngredient"
    ADD CONSTRAINT "RecipeIngredient_id_MeasureType_fkey" FOREIGN KEY ("id_MeasureType") REFERENCES public."MeasureType"(id);


--
-- TOC entry 3310 (class 2606 OID 17190)
-- Name: RecipeIngredient RecipeIngredient_id_Product_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeIngredient"
    ADD CONSTRAINT "RecipeIngredient_id_Product_fkey" FOREIGN KEY ("id_Product") REFERENCES public."Product"(id);


--
-- TOC entry 3311 (class 2606 OID 17195)
-- Name: RecipeIngredient RecipeIngredient_id_Recipe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RecipeIngredient"
    ADD CONSTRAINT "RecipeIngredient_id_Recipe_fkey" FOREIGN KEY ("id_Recipe") REFERENCES public."Recipe"(id);


--
-- TOC entry 3305 (class 2606 OID 17170)
-- Name: Recipe Recipe_id_CreatorUser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Recipe"
    ADD CONSTRAINT "Recipe_id_CreatorUser_fkey" FOREIGN KEY ("id_CreatorUser") REFERENCES public."User"(id);


--
-- TOC entry 3316 (class 2606 OID 17225)
-- Name: UserCredential UserCredential_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserCredential"
    ADD CONSTRAINT "UserCredential_id_fkey" FOREIGN KEY (id) REFERENCES public."User"(id);


--
-- TOC entry 3314 (class 2606 OID 17220)
-- Name: UserProduct UserProduct_id_Product_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserProduct"
    ADD CONSTRAINT "UserProduct_id_Product_fkey" FOREIGN KEY ("id_Product") REFERENCES public."Product"(id);


--
-- TOC entry 3315 (class 2606 OID 17215)
-- Name: UserProduct UserProduct_id_User_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserProduct"
    ADD CONSTRAINT "UserProduct_id_User_fkey" FOREIGN KEY ("id_User") REFERENCES public."User"(id);


--
-- TOC entry 3312 (class 2606 OID 17210)
-- Name: UserRecipe UserRecipe_id_Recipe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRecipe"
    ADD CONSTRAINT "UserRecipe_id_Recipe_fkey" FOREIGN KEY ("id_Recipe") REFERENCES public."Recipe"(id);


--
-- TOC entry 3313 (class 2606 OID 17205)
-- Name: UserRecipe UserRecipe_id_User_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRecipe"
    ADD CONSTRAINT "UserRecipe_id_User_fkey" FOREIGN KEY ("id_User") REFERENCES public."User"(id);


--
-- TOC entry 3308 (class 2606 OID 17185)
-- Name: User User_id_userRole_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_id_userRole_fkey" FOREIGN KEY ("id_userRole") REFERENCES public."UserRole"(id);


--
-- TOC entry 3494 (class 0 OID 0)
-- Dependencies: 238
-- Name: TABLE "Category"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."Category" TO dimon;


--
-- TOC entry 3496 (class 0 OID 0)
-- Dependencies: 237
-- Name: SEQUENCE "Category_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."Category_id_seq" TO dimon;


--
-- TOC entry 3497 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE "CookStep"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."CookStep" TO dimon;


--
-- TOC entry 3499 (class 0 OID 0)
-- Dependencies: 217
-- Name: SEQUENCE "CookStep_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."CookStep_id_seq" TO dimon;


--
-- TOC entry 3500 (class 0 OID 0)
-- Dependencies: 236
-- Name: TABLE "MeasureType"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."MeasureType" TO dimon;


--
-- TOC entry 3502 (class 0 OID 0)
-- Dependencies: 235
-- Name: SEQUENCE "MeasureType_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."MeasureType_id_seq" TO dimon;


--
-- TOC entry 3503 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE "Product"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."Product" TO dimon;


--
-- TOC entry 3504 (class 0 OID 0)
-- Dependencies: 230
-- Name: TABLE "ProductType"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."ProductType" TO dimon;


--
-- TOC entry 3506 (class 0 OID 0)
-- Dependencies: 229
-- Name: SEQUENCE "ProductType_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."ProductType_id_seq" TO dimon;


--
-- TOC entry 3508 (class 0 OID 0)
-- Dependencies: 219
-- Name: SEQUENCE "Product_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."Product_id_seq" TO dimon;


--
-- TOC entry 3509 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE "Recipe"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."Recipe" TO dimon;


--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 240
-- Name: TABLE "RecipeCategory"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."RecipeCategory" TO dimon;


--
-- TOC entry 3512 (class 0 OID 0)
-- Dependencies: 239
-- Name: SEQUENCE "RecipeCategory_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."RecipeCategory_id_seq" TO dimon;


--
-- TOC entry 3513 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE "RecipeIngredient"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."RecipeIngredient" TO dimon;


--
-- TOC entry 3515 (class 0 OID 0)
-- Dependencies: 223
-- Name: SEQUENCE "RecipeIngredient_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."RecipeIngredient_id_seq" TO dimon;


--
-- TOC entry 3517 (class 0 OID 0)
-- Dependencies: 215
-- Name: SEQUENCE "Recipe_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."Recipe_id_seq" TO dimon;


--
-- TOC entry 3518 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE "User"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."User" TO dimon;


--
-- TOC entry 3519 (class 0 OID 0)
-- Dependencies: 232
-- Name: TABLE "UserCredential"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."UserCredential" TO dimon;


--
-- TOC entry 3521 (class 0 OID 0)
-- Dependencies: 231
-- Name: SEQUENCE "UserCredential_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."UserCredential_id_seq" TO dimon;


--
-- TOC entry 3522 (class 0 OID 0)
-- Dependencies: 228
-- Name: TABLE "UserProduct"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."UserProduct" TO dimon;


--
-- TOC entry 3524 (class 0 OID 0)
-- Dependencies: 227
-- Name: SEQUENCE "UserProduct_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."UserProduct_id_seq" TO dimon;


--
-- TOC entry 3525 (class 0 OID 0)
-- Dependencies: 226
-- Name: TABLE "UserRecipe"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."UserRecipe" TO dimon;


--
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 225
-- Name: SEQUENCE "UserRecipe_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."UserRecipe_id_seq" TO dimon;


--
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 234
-- Name: TABLE "UserRole"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."UserRole" TO dimon;


--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 233
-- Name: SEQUENCE "UserRole_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."UserRole_id_seq" TO dimon;


--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 221
-- Name: SEQUENCE "User_id_seq"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public."User_id_seq" TO dimon;


--
-- TOC entry 2100 (class 826 OID 17064)
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON SEQUENCES TO dimon;


--
-- TOC entry 2099 (class 826 OID 17063)
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON FUNCTIONS TO dimon;


--
-- TOC entry 2098 (class 826 OID 17062)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES TO dimon;


-- Completed on 2024-04-24 01:03:54

--
-- PostgreSQL database dump complete
--


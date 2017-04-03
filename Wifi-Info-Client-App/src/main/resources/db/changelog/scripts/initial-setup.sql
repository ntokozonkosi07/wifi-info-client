--
-- TOC entry 175 (class 1259 OID 25879)
-- Name: advert_category_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE advert_category_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 176 (class 1259 OID 25881)
-- Name: advert_data_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE advert_data_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 177 (class 1259 OID 25883)
-- Name: advert_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE advert_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 178 (class 1259 OID 25885)
-- Name: download_page_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE download_page_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 181 (class 1259 OID 25891)
-- Name: l_advert; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_advert (
    advert_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    advert_type character varying(255) NOT NULL,
    advert_ref_no character varying(255),
    effective_to timestamp without time zone,
    effective_from timestamp without time zone NOT NULL,
    advert_data_ref bigint NOT NULL,
    advert_link_data_ref bigint
);

--
-- TOC entry 182 (class 1259 OID 25899)
-- Name: l_advert_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_advert_category (
    advert_category_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    advert_ref bigint NOT NULL,
    category_ref bigint NOT NULL
);

--
-- TOC entry 183 (class 1259 OID 25904)
-- Name: l_advert_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_advert_data (
    advert_data_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    binary_data bytea NOT NULL,
    file_name character varying(255) NOT NULL,
    file_type character varying(255) NOT NULL
);

--
-- TOC entry 184 (class 1259 OID 25912)
-- Name: l_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_category (
    category_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    category_name character varying(80) NOT NULL
);

--
-- TOC entry 185 (class 1259 OID 25917)
-- Name: l_download_page; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_download_page (
    download_page_ref bigint NOT NULL,
    binary_data bytea NOT NULL
);

--
-- TOC entry 186 (class 1259 OID 25925)
-- Name: l_node; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_node (
    node_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    category_name character varying(80),
    node_category_ref bigint,
    device_ref character varying(255),
    mac_address character varying(255),
    node_address character varying(255),
    node_name character varying(255)
);

--
-- TOC entry 187 (class 1259 OID 25933)
-- Name: l_node_banner; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_node_banner (
    node_ref character varying(255) NOT NULL,
    effective_to timestamp without time zone,
    effective_from timestamp without time zone NOT NULL,
    node_data_ref bigint NOT NULL,
    node_link_data_ref bigint
);

--
-- TOC entry 188 (class 1259 OID 25938)
-- Name: l_node_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_node_data (
    node_data_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    binary_data bytea NOT NULL,
    file_name character varying(255) NOT NULL,
    file_type character varying(255) NOT NULL
);

--
-- TOC entry 179 (class 1259 OID 25887)
-- Name: node_data_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE node_data_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 180 (class 1259 OID 25889)
-- Name: node_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE node_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 175
-- Name: advert_category_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('advert_category_seq', 1, false);

--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 176
-- Name: advert_data_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('advert_data_seq', 1, false);

--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 177
-- Name: advert_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('advert_seq', 1, false);

--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 178
-- Name: download_page_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('download_page_seq', 1, false);

--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 179
-- Name: node_data_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('node_data_seq', 1, false);

--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 180
-- Name: node_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('node_seq', 1, true);

--
-- TOC entry 2204 (class 2606 OID 25903)
-- Name: l_advert_category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT l_advert_category_pkey PRIMARY KEY (advert_category_ref);

--
-- TOC entry 2206 (class 2606 OID 25911)
-- Name: l_advert_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_data
    ADD CONSTRAINT l_advert_data_pkey PRIMARY KEY (advert_data_ref);

--
-- TOC entry 2202 (class 2606 OID 25898)
-- Name: l_advert_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert
    ADD CONSTRAINT l_advert_pkey PRIMARY KEY (advert_ref);

--
-- TOC entry 2208 (class 2606 OID 25916)
-- Name: l_category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_category
    ADD CONSTRAINT l_category_pkey PRIMARY KEY (category_ref);

--
-- TOC entry 2210 (class 2606 OID 25924)
-- Name: l_download_page_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_download_page
    ADD CONSTRAINT l_download_page_pkey PRIMARY KEY (download_page_ref);

--
-- TOC entry 2214 (class 2606 OID 25937)
-- Name: l_node_banner_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_banner
    ADD CONSTRAINT l_node_banner_pkey PRIMARY KEY (node_ref);

--
-- TOC entry 2216 (class 2606 OID 25945)
-- Name: l_node_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_data
    ADD CONSTRAINT l_node_data_pkey PRIMARY KEY (node_data_ref);

--
-- TOC entry 2212 (class 2606 OID 25932)
-- Name: l_node_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node
    ADD CONSTRAINT l_node_pkey PRIMARY KEY (node_ref);

--
-- TOC entry 2221 (class 2606 OID 25966)
-- Name: fk1ujiw8sxr9u32ixxfw2xh7tl0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT l_advert_category_fkey1 FOREIGN KEY (advert_ref) REFERENCES l_advert(advert_ref);

--
-- TOC entry 2220 (class 2606 OID 25961)
-- Name: fkdwe71kdwlknryx0t9ux9qpon7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT l_advert_category_fkey2 FOREIGN KEY (category_ref) REFERENCES l_category(category_ref);

--
-- TOC entry 2222 (class 2606 OID 25971)
-- Name: fk35eofvrqpskr8khjwb340m7a9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_banner
    ADD CONSTRAINT l_node_banner_fkey1 FOREIGN KEY (node_data_ref) REFERENCES l_node_data(node_data_ref);

--
-- TOC entry 2223 (class 2606 OID 25976)
-- Name: fka07i60jwpyo5puiobpuiimnrd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_banner
    ADD CONSTRAINT l_node_banner_fkey2 FOREIGN KEY (node_link_data_ref) REFERENCES l_node_data(node_data_ref);

--
-- TOC entry 2217 (class 2606 OID 25946)
-- Name: fkepi3ekn84to997msclju352p; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert
    ADD CONSTRAINT l_advert_fkey1 FOREIGN KEY (advert_data_ref) REFERENCES l_advert_data(advert_data_ref);

--
-- TOC entry 2218 (class 2606 OID 25951)
-- Name: fkn0s83fle3owv0ak96h34x8v5a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert
    ADD CONSTRAINT l_advert_fkey2 FOREIGN KEY (advert_link_data_ref) REFERENCES l_advert_data(advert_data_ref);

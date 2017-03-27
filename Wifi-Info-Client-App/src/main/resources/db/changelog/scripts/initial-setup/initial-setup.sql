--liquibase formatted sql
--changeset author:zifa@wifiinfo.co.za id:initial-setup.sql

CREATE SEQUENCE advert_category_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 174 (class 1259 OID 25105)
-- Name: advert_data_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE advert_data_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 175 (class 1259 OID 25107)
-- Name: advert_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE advert_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 176 (class 1259 OID 25109)
-- Name: download_page_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE download_page_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 179 (class 1259 OID 25115)
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
-- TOC entry 180 (class 1259 OID 25123)
-- Name: l_advert_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_advert_category (
    advert_category_ref bigint NOT NULL,
    created_by bigint,
    created_date timestamp without time zone,
    last_modified_by bigint,
    last_modified_date timestamp without time zone,
    advert_ref bigint NOT NULL,
    category_ref bigint NOT NULL,
    advert_categories_advert_ref bigint
);

--
-- TOC entry 181 (class 1259 OID 25128)
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
-- TOC entry 182 (class 1259 OID 25136)
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
-- TOC entry 183 (class 1259 OID 25141)
-- Name: l_download_page; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE l_download_page (
    download_page_ref bigint NOT NULL,
    binary_data bytea NOT NULL
);

--
-- TOC entry 184 (class 1259 OID 25149)
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
-- TOC entry 185 (class 1259 OID 25157)
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
-- TOC entry 186 (class 1259 OID 25162)
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
-- TOC entry 177 (class 1259 OID 25111)
-- Name: node_data_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE node_data_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 178 (class 1259 OID 25113)
-- Name: node_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE node_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 2193 (class 2606 OID 25127)
-- Name: l_advert_category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT l_advert_category_pkey PRIMARY KEY (advert_category_ref);

--
-- TOC entry 2195 (class 2606 OID 25135)
-- Name: l_advert_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_data
    ADD CONSTRAINT l_advert_data_pkey PRIMARY KEY (advert_data_ref);

--
-- TOC entry 2191 (class 2606 OID 25122)
-- Name: l_advert_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert
    ADD CONSTRAINT l_advert_pkey PRIMARY KEY (advert_ref);

--
-- TOC entry 2197 (class 2606 OID 25140)
-- Name: l_category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_category
    ADD CONSTRAINT l_category_pkey PRIMARY KEY (category_ref);

--
-- TOC entry 2199 (class 2606 OID 25148)
-- Name: l_download_page_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_download_page
    ADD CONSTRAINT l_download_page_pkey PRIMARY KEY (download_page_ref);

--
-- TOC entry 2203 (class 2606 OID 25161)
-- Name: l_node_banner_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_banner
    ADD CONSTRAINT l_node_banner_pkey PRIMARY KEY (node_ref);

--
-- TOC entry 2205 (class 2606 OID 25169)
-- Name: l_node_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_data
    ADD CONSTRAINT l_node_data_pkey PRIMARY KEY (node_data_ref);

--
-- TOC entry 2201 (class 2606 OID 25156)
-- Name: l_node_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node
    ADD CONSTRAINT l_node_pkey PRIMARY KEY (node_ref);

--
-- TOC entry 2211 (class 2606 OID 25195)
-- Name: fk35eofvrqpskr8khjwb340m7a9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_banner
    ADD CONSTRAINT fk35eofvrqpskr8khjwb340m7a9 FOREIGN KEY (node_data_ref) REFERENCES l_node_data(node_data_ref);

--
-- TOC entry 2212 (class 2606 OID 25200)
-- Name: fka07i60jwpyo5puiobpuiimnrd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_node_banner
    ADD CONSTRAINT fka07i60jwpyo5puiobpuiimnrd FOREIGN KEY (node_link_data_ref) REFERENCES l_node_data(node_data_ref);

--
-- TOC entry 2209 (class 2606 OID 25185)
-- Name: fkdwe71kdwlknryx0t9ux9qpon7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT fkdwe71kdwlknryx0t9ux9qpon7 FOREIGN KEY (category_ref) REFERENCES l_category(category_ref);

--
-- TOC entry 2206 (class 2606 OID 25170)
-- Name: fkepi3ekn84to997msclju352p; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert
    ADD CONSTRAINT fkepi3ekn84to997msclju352p FOREIGN KEY (advert_data_ref) REFERENCES l_advert_data(advert_data_ref);

--
-- TOC entry 2208 (class 2606 OID 25180)
-- Name: fkf0qmme6440pfb4ia8tuvksow7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT fkf0qmme6440pfb4ia8tuvksow7 FOREIGN KEY (advert_ref) REFERENCES l_advert(advert_ref);

--
-- TOC entry 2207 (class 2606 OID 25175)
-- Name: fkn0s83fle3owv0ak96h34x8v5a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert
    ADD CONSTRAINT fkn0s83fle3owv0ak96h34x8v5a FOREIGN KEY (advert_link_data_ref) REFERENCES l_advert_data(advert_data_ref);

--
-- TOC entry 2210 (class 2606 OID 25190)
-- Name: fkrjedg5vldnb5qbm6g0tsprihg; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY l_advert_category
    ADD CONSTRAINT fkrjedg5vldnb5qbm6g0tsprihg FOREIGN KEY (advert_categories_advert_ref) REFERENCES l_advert(advert_ref);

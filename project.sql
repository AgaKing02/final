PGDMP                     
    x            Project    13.0    13.0 #    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16394    Project    DATABASE     j   CREATE DATABASE "Project" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Kazakhstan.1251';
    DROP DATABASE "Project";
                postgres    false            �            1259    16512    clubs    TABLE     �   CREATE TABLE public.clubs (
    c_id integer NOT NULL,
    c_name character varying(255),
    e_description character varying(255)
);
    DROP TABLE public.clubs;
       public         heap    postgres    false            �            1259    16520    clubstudent    TABLE     p   CREATE TABLE public.clubstudent (
    cs_id integer NOT NULL,
    club_id integer,
    volunteers_id integer
);
    DROP TABLE public.clubstudent;
       public         heap    postgres    false            �            1259    16538    events    TABLE     �   CREATE TABLE public.events (
    e_id integer NOT NULL,
    e_event character varying(255),
    e_description character varying(255)
);
    DROP TABLE public.events;
       public         heap    postgres    false            �            1259    16546    eventstudent    TABLE     o   CREATE TABLE public.eventstudent (
    e_id integer NOT NULL,
    event_id integer,
    listener_id integer
);
     DROP TABLE public.eventstudent;
       public         heap    postgres    false            �            1259    16507    groups    TABLE     q   CREATE TABLE public.groups (
    g_id integer NOT NULL,
    g_name character varying(255),
    g_year integer
);
    DROP TABLE public.groups;
       public         heap    postgres    false            �            1259    16525    groupstudent    TABLE     o   CREATE TABLE public.groupstudent (
    gs_id integer NOT NULL,
    group_id integer,
    student_id integer
);
     DROP TABLE public.groupstudent;
       public         heap    postgres    false            �            1259    16530    news    TABLE     �   CREATE TABLE public.news (
    n_id integer NOT NULL,
    n_title character varying(255),
    n_description character varying(255),
    publisher integer
);
    DROP TABLE public.news;
       public         heap    postgres    false            �            1259    16499    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(255),
    name character varying(255),
    surname character varying(255),
    password character varying(255),
    role character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    16512    clubs 
   TABLE DATA           <   COPY public.clubs (c_id, c_name, e_description) FROM stdin;
    public          postgres    false    202   �'       �          0    16520    clubstudent 
   TABLE DATA           D   COPY public.clubstudent (cs_id, club_id, volunteers_id) FROM stdin;
    public          postgres    false    203   �'       �          0    16538    events 
   TABLE DATA           >   COPY public.events (e_id, e_event, e_description) FROM stdin;
    public          postgres    false    206   �'       �          0    16546    eventstudent 
   TABLE DATA           C   COPY public.eventstudent (e_id, event_id, listener_id) FROM stdin;
    public          postgres    false    207   �'       �          0    16507    groups 
   TABLE DATA           6   COPY public.groups (g_id, g_name, g_year) FROM stdin;
    public          postgres    false    201   (       �          0    16525    groupstudent 
   TABLE DATA           C   COPY public.groupstudent (gs_id, group_id, student_id) FROM stdin;
    public          postgres    false    204   3(       �          0    16530    news 
   TABLE DATA           G   COPY public.news (n_id, n_title, n_description, publisher) FROM stdin;
    public          postgres    false    205   P(       �          0    16499    users 
   TABLE DATA           L   COPY public.users (id, username, name, surname, password, role) FROM stdin;
    public          postgres    false    200   m(       E           2606    16519    clubs clubs_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.clubs
    ADD CONSTRAINT clubs_pkey PRIMARY KEY (c_id);
 :   ALTER TABLE ONLY public.clubs DROP CONSTRAINT clubs_pkey;
       public            postgres    false    202            G           2606    16524    clubstudent clubstudent_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.clubstudent
    ADD CONSTRAINT clubstudent_pkey PRIMARY KEY (cs_id);
 F   ALTER TABLE ONLY public.clubstudent DROP CONSTRAINT clubstudent_pkey;
       public            postgres    false    203            M           2606    16545    events events_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (e_id);
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT events_pkey;
       public            postgres    false    206            O           2606    16550    eventstudent eventstudent_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.eventstudent
    ADD CONSTRAINT eventstudent_pkey PRIMARY KEY (e_id);
 H   ALTER TABLE ONLY public.eventstudent DROP CONSTRAINT eventstudent_pkey;
       public            postgres    false    207            C           2606    16511    groups groups_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (g_id);
 <   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_pkey;
       public            postgres    false    201            I           2606    16529    groupstudent groupstudent_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.groupstudent
    ADD CONSTRAINT groupstudent_pkey PRIMARY KEY (gs_id);
 H   ALTER TABLE ONLY public.groupstudent DROP CONSTRAINT groupstudent_pkey;
       public            postgres    false    204            K           2606    16537    news news_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (n_id);
 8   ALTER TABLE ONLY public.news DROP CONSTRAINT news_pkey;
       public            postgres    false    205            A           2606    16506    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    200            Q           2606    16581 $   clubstudent clubstudent_club_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.clubstudent
    ADD CONSTRAINT clubstudent_club_id_fkey FOREIGN KEY (club_id) REFERENCES public.clubs(c_id);
 N   ALTER TABLE ONLY public.clubstudent DROP CONSTRAINT clubstudent_club_id_fkey;
       public          postgres    false    203    202    2885            P           2606    16576 *   clubstudent clubstudent_volunteers_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.clubstudent
    ADD CONSTRAINT clubstudent_volunteers_id_fkey FOREIGN KEY (volunteers_id) REFERENCES public.users(id);
 T   ALTER TABLE ONLY public.clubstudent DROP CONSTRAINT clubstudent_volunteers_id_fkey;
       public          postgres    false    200    203    2881            V           2606    16571 '   eventstudent eventstudent_event_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.eventstudent
    ADD CONSTRAINT eventstudent_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.events(e_id);
 Q   ALTER TABLE ONLY public.eventstudent DROP CONSTRAINT eventstudent_event_id_fkey;
       public          postgres    false    207    2893    206            U           2606    16566 *   eventstudent eventstudent_listener_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.eventstudent
    ADD CONSTRAINT eventstudent_listener_id_fkey FOREIGN KEY (listener_id) REFERENCES public.users(id);
 T   ALTER TABLE ONLY public.eventstudent DROP CONSTRAINT eventstudent_listener_id_fkey;
       public          postgres    false    200    207    2881            S           2606    16556 '   groupstudent groupstudent_group_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.groupstudent
    ADD CONSTRAINT groupstudent_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.groups(g_id);
 Q   ALTER TABLE ONLY public.groupstudent DROP CONSTRAINT groupstudent_group_id_fkey;
       public          postgres    false    2883    204    201            R           2606    16551 )   groupstudent groupstudent_student_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.groupstudent
    ADD CONSTRAINT groupstudent_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.groupstudent DROP CONSTRAINT groupstudent_student_id_fkey;
       public          postgres    false    204    2881    200            T           2606    16561    news news_publisher_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_publisher_fkey FOREIGN KEY (publisher) REFERENCES public.users(id);
 B   ALTER TABLE ONLY public.news DROP CONSTRAINT news_publisher_fkey;
       public          postgres    false    2881    205    200            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     
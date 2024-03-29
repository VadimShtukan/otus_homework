
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

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE public.application_daily_active_users (
    applications_id uuid NOT NULL,
    count integer NOT NULL,
    day integer NOT NULL
);


ALTER TABLE public.application_daily_active_users OWNER TO fusionauth;


CREATE TABLE public.application_monthly_active_users (
    applications_id uuid NOT NULL,
    count integer NOT NULL,
    month integer NOT NULL
);


ALTER TABLE public.application_monthly_active_users OWNER TO fusionauth;

CREATE TABLE public.application_registration_counts (
    applications_id uuid NOT NULL,
    count integer NOT NULL,
    decremented_count integer NOT NULL,
    hour integer NOT NULL
);


ALTER TABLE public.application_registration_counts OWNER TO fusionauth;


CREATE TABLE public.application_roles (
    id uuid NOT NULL,
    applications_id uuid NOT NULL,
    description character varying(255),
    is_default boolean NOT NULL,
    is_super_role boolean NOT NULL,
    name character varying(191) NOT NULL
);


ALTER TABLE public.application_roles OWNER TO fusionauth;


CREATE TABLE public.applications (
    id uuid NOT NULL,
    access_token_populate_lambdas_id uuid,
    access_token_signing_keys_id uuid,
    active boolean NOT NULL,
    data text NOT NULL,
    id_token_populate_lambdas_id uuid,
    id_token_signing_keys_id uuid,
    name character varying(191) NOT NULL,
    samlv2_issuer character varying(191),
    samlv2_keys_id uuid,
    samlv2_populate_lambdas_id uuid,
    tenants_id uuid NOT NULL,
    verification_email_templates_id uuid
);


ALTER TABLE public.applications OWNER TO fusionauth;


CREATE TABLE public.audit_logs (
    id bigint NOT NULL,
    insert_instant bigint NOT NULL,
    insert_user character varying(255) NOT NULL,
    message text NOT NULL,
    data text
);


ALTER TABLE public.audit_logs OWNER TO fusionauth;



CREATE SEQUENCE public.audit_logs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.audit_logs_id_seq OWNER TO fusionauth;



ALTER SEQUENCE public.audit_logs_id_seq OWNED BY public.audit_logs.id;



CREATE TABLE public.authentication_keys (
    id character varying(191) NOT NULL,
    permissions text,
    meta_data text,
    tenants_id uuid
);


ALTER TABLE public.authentication_keys OWNER TO fusionauth;


CREATE TABLE public.breached_password_metrics (
    tenants_id uuid NOT NULL,
    matched_exact_count integer NOT NULL,
    matched_sub_address_count integer NOT NULL,
    matched_common_password_count integer NOT NULL,
    matched_password_count integer NOT NULL,
    passwords_checked_count integer NOT NULL
);


ALTER TABLE public.breached_password_metrics OWNER TO fusionauth;


CREATE TABLE public.clean_speak_applications (
    applications_id uuid NOT NULL,
    clean_speak_application_id uuid NOT NULL
);


ALTER TABLE public.clean_speak_applications OWNER TO fusionauth;


CREATE TABLE public.common_breached_passwords (
    password character varying(191) NOT NULL
);


ALTER TABLE public.common_breached_passwords OWNER TO fusionauth;



CREATE TABLE public.consents (
    id uuid NOT NULL,
    consent_email_templates_id uuid,
    data text,
    name character varying(191) NOT NULL,
    email_plus_email_templates_id uuid
);


ALTER TABLE public.consents OWNER TO fusionauth;


CREATE TABLE public.data_sets (
    name character varying(191) NOT NULL,
    last_update_instant bigint NOT NULL
);


ALTER TABLE public.data_sets OWNER TO fusionauth;


CREATE TABLE public.email_templates (
    id uuid NOT NULL,
    default_from_name character varying(255),
    default_html_template text NOT NULL,
    default_subject character varying(255) NOT NULL,
    default_text_template text NOT NULL,
    from_email character varying(255),
    localized_from_names text,
    localized_html_templates text NOT NULL,
    localized_subjects text NOT NULL,
    localized_text_templates text NOT NULL,
    name character varying(191) NOT NULL
);


ALTER TABLE public.email_templates OWNER TO fusionauth;


CREATE TABLE public.event_logs (
    id bigint NOT NULL,
    insert_instant bigint NOT NULL,
    message text NOT NULL,
    type smallint NOT NULL
);


ALTER TABLE public.event_logs OWNER TO fusionauth;



CREATE SEQUENCE public.event_logs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_logs_id_seq OWNER TO fusionauth;


ALTER SEQUENCE public.event_logs_id_seq OWNED BY public.event_logs.id;




CREATE TABLE public.external_identifiers (
    id character varying(191) NOT NULL,
    applications_id uuid,
    data text,
    insert_instant bigint NOT NULL,
    tenants_id uuid NOT NULL,
    type smallint NOT NULL,
    users_id uuid
);


ALTER TABLE public.external_identifiers OWNER TO fusionauth;


CREATE TABLE public.failed_logins (
    count integer NOT NULL,
    last_failed_instant bigint NOT NULL,
    tenants_id uuid NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.failed_logins OWNER TO fusionauth;



CREATE TABLE public.families (
    data text,
    family_id uuid NOT NULL,
    insert_instant bigint NOT NULL,
    owner boolean NOT NULL,
    role smallint NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.families OWNER TO fusionauth;



CREATE TABLE public.federated_domains (
    identity_providers_id uuid NOT NULL,
    domain character varying(191) NOT NULL
);


ALTER TABLE public.federated_domains OWNER TO fusionauth;



CREATE TABLE public.global_daily_active_users (
    count integer NOT NULL,
    day integer NOT NULL
);


ALTER TABLE public.global_daily_active_users OWNER TO fusionauth;



CREATE TABLE public.global_monthly_active_users (
    count integer NOT NULL,
    month integer NOT NULL
);


ALTER TABLE public.global_monthly_active_users OWNER TO fusionauth;


CREATE TABLE public.global_registration_counts (
    count integer NOT NULL,
    decremented_count integer NOT NULL,
    hour integer NOT NULL
);


ALTER TABLE public.global_registration_counts OWNER TO fusionauth;


CREATE TABLE public.group_application_roles (
    application_roles_id uuid NOT NULL,
    groups_id uuid NOT NULL
);


ALTER TABLE public.group_application_roles OWNER TO fusionauth;



CREATE TABLE public.group_members (
    id uuid NOT NULL,
    groups_id uuid NOT NULL,
    data text,
    insert_instant bigint NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.group_members OWNER TO fusionauth;



CREATE TABLE public.groups (
    id uuid NOT NULL,
    data text,
    name character varying(191) NOT NULL,
    tenants_id uuid NOT NULL
);


ALTER TABLE public.groups OWNER TO fusionauth;



CREATE TABLE public.hourly_logins (
    applications_id uuid NOT NULL,
    count integer NOT NULL,
    data text,
    hour integer NOT NULL
);


ALTER TABLE public.hourly_logins OWNER TO fusionauth;



CREATE TABLE public.identities (
    id bigint NOT NULL,
    breached_password_last_checked_instant bigint,
    breached_password_status smallint,
    email character varying(191),
    encryption_scheme character varying(255) NOT NULL,
    factor integer NOT NULL,
    last_login_instant bigint,
    password character varying(255) NOT NULL,
    password_change_reason smallint,
    password_change_required boolean NOT NULL,
    password_last_update_instant bigint NOT NULL,
    salt character varying(255) NOT NULL,
    status smallint NOT NULL,
    tenants_id uuid NOT NULL,
    two_factor_delivery smallint NOT NULL,
    two_factor_enabled boolean NOT NULL,
    two_factor_secret character varying(255),
    username character varying(191),
    username_index character varying(191),
    username_status smallint NOT NULL,
    users_id uuid NOT NULL,
    verified boolean NOT NULL
);


ALTER TABLE public.identities OWNER TO fusionauth;


CREATE SEQUENCE public.identities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.identities_id_seq OWNER TO fusionauth;



ALTER SEQUENCE public.identities_id_seq OWNED BY public.identities.id;



CREATE TABLE public.identity_providers (
    id uuid NOT NULL,
    data text NOT NULL,
    enabled boolean NOT NULL,
    name character varying(191) NOT NULL,
    type character varying(255) NOT NULL,
    keys_id uuid,
    reconcile_lambdas_id uuid
);


ALTER TABLE public.identity_providers OWNER TO fusionauth;


CREATE TABLE public.identity_providers_applications (
    applications_id uuid NOT NULL,
    identity_providers_id uuid NOT NULL,
    data text NOT NULL,
    enabled boolean NOT NULL
);


ALTER TABLE public.identity_providers_applications OWNER TO fusionauth;


CREATE TABLE public.instance (
    id uuid NOT NULL,
    encryption_key character varying(255),
    license_id character varying(255)
);


ALTER TABLE public.instance OWNER TO fusionauth;


CREATE TABLE public.integrations (
    data text NOT NULL
);


ALTER TABLE public.integrations OWNER TO fusionauth;


CREATE TABLE public.keys (
    id uuid NOT NULL,
    algorithm character varying(10),
    certificate text,
    expiration_instant bigint,
    insert_instant bigint NOT NULL,
    issuer character varying(255),
    kid character varying(191) NOT NULL,
    name character varying(191) NOT NULL,
    private_key text,
    public_key text,
    secret text,
    type character varying(10) NOT NULL
);


ALTER TABLE public.keys OWNER TO fusionauth;


CREATE TABLE public.lambdas (
    id uuid NOT NULL,
    body text NOT NULL,
    debug boolean NOT NULL,
    enabled boolean NOT NULL,
    insert_instant bigint NOT NULL,
    name character varying(255) NOT NULL,
    type smallint NOT NULL
);


ALTER TABLE public.lambdas OWNER TO fusionauth;


CREATE TABLE public.locks (
    type character varying(191) NOT NULL,
    update_instant bigint
);


ALTER TABLE public.locks OWNER TO fusionauth;


CREATE TABLE public.master_record (
    id uuid NOT NULL,
    instant bigint NOT NULL
);


ALTER TABLE public.master_record OWNER TO fusionauth;


CREATE TABLE public.migrations (
    name character varying(191) NOT NULL,
    run_instant bigint NOT NULL
);


ALTER TABLE public.migrations OWNER TO fusionauth;


CREATE TABLE public.nodes (
    id uuid NOT NULL,
    insert_instant bigint NOT NULL,
    last_checkin_instant bigint NOT NULL,
    runtime_mode character varying(255) NOT NULL,
    url character varying(255) NOT NULL
);


ALTER TABLE public.nodes OWNER TO fusionauth;


CREATE TABLE public.previous_passwords (
    create_instant bigint NOT NULL,
    encryption_scheme character varying(255) NOT NULL,
    factor integer NOT NULL,
    password character varying(255) NOT NULL,
    salt character varying(255) NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.previous_passwords OWNER TO fusionauth;


CREATE TABLE public.raw_application_daily_active_users (
    applications_id uuid NOT NULL,
    day integer NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.raw_application_daily_active_users OWNER TO fusionauth;


CREATE TABLE public.raw_application_monthly_active_users (
    applications_id uuid NOT NULL,
    month integer NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.raw_application_monthly_active_users OWNER TO fusionauth;


CREATE TABLE public.raw_global_daily_active_users (
    day integer NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.raw_global_daily_active_users OWNER TO fusionauth;


CREATE TABLE public.raw_global_monthly_active_users (
    month integer NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.raw_global_monthly_active_users OWNER TO fusionauth;


CREATE TABLE public.raw_logins (
    applications_id uuid,
    instant bigint NOT NULL,
    ip_address character varying(255),
    users_id uuid NOT NULL
);


ALTER TABLE public.raw_logins OWNER TO fusionauth;

CREATE TABLE public.refresh_tokens (
    token character varying(191) NOT NULL,
    users_id uuid NOT NULL,
    applications_id uuid NOT NULL,
    insert_instant bigint NOT NULL,
    start_instant bigint NOT NULL,
    meta_data text NOT NULL
);


ALTER TABLE public.refresh_tokens OWNER TO fusionauth;


CREATE TABLE public.system_configuration (
    data text NOT NULL,
    report_timezone character varying(255) NOT NULL
);


ALTER TABLE public.system_configuration OWNER TO fusionauth;


CREATE TABLE public.tenants (
    id uuid NOT NULL,
    access_token_signing_keys_id uuid NOT NULL,
    confirm_child_email_template_id uuid,
    data text,
    failed_authentication_user_actions_id uuid,
    family_request_email_template_id uuid,
    forgot_password_email_templates_id uuid,
    id_token_signing_keys_id uuid NOT NULL,
    name character varying(191) NOT NULL,
    parent_registration_email_template_id uuid,
    passwordless_email_templates_id uuid,
    set_password_email_templates_id uuid,
    themes_id uuid NOT NULL,
    verification_email_templates_id uuid
);


ALTER TABLE public.tenants OWNER TO fusionauth;


CREATE TABLE public.themes (
    id uuid NOT NULL,
    data text NOT NULL,
    insert_instant bigint NOT NULL,
    last_update_instant bigint NOT NULL,
    name character varying(191) NOT NULL
);


ALTER TABLE public.themes OWNER TO fusionauth;


CREATE TABLE public.user_action_logs (
    id uuid NOT NULL,
    actioner_users_id uuid,
    actionee_users_id uuid NOT NULL,
    comment text,
    create_instant bigint NOT NULL,
    email_user_on_end boolean NOT NULL,
    end_event_sent boolean,
    expiry bigint,
    history text,
    localized_name character varying(191),
    localized_option character varying(255),
    localized_reason character varying(255),
    name character varying(191),
    notify_user_on_end boolean NOT NULL,
    option_name character varying(255),
    reason character varying(255),
    reason_code character varying(255),
    user_actions_id uuid NOT NULL
);


ALTER TABLE public.user_action_logs OWNER TO fusionauth;


CREATE TABLE public.user_action_logs_applications (
    applications_id uuid NOT NULL,
    user_action_logs_id uuid NOT NULL
);


ALTER TABLE public.user_action_logs_applications OWNER TO fusionauth;


CREATE TABLE public.user_action_reasons (
    id uuid NOT NULL,
    localized_texts text,
    text character varying(191) NOT NULL,
    code character varying(191) NOT NULL
);


ALTER TABLE public.user_action_reasons OWNER TO fusionauth;

CREATE TABLE public.user_actions (
    id uuid NOT NULL,
    active boolean NOT NULL,
    cancel_email_templates_id uuid,
    end_email_templates_id uuid,
    include_email_in_event_json boolean NOT NULL,
    localized_names text,
    modify_email_templates_id uuid,
    name character varying(191) NOT NULL,
    options text,
    prevent_login boolean NOT NULL,
    send_end_event boolean NOT NULL,
    start_email_templates_id uuid,
    temporal boolean NOT NULL,
    transaction_type smallint NOT NULL,
    user_notifications_enabled boolean NOT NULL,
    user_emailing_enabled boolean NOT NULL
);


ALTER TABLE public.user_actions OWNER TO fusionauth;


CREATE TABLE public.user_comments (
    id uuid NOT NULL,
    comment text,
    commenter_id uuid NOT NULL,
    create_instant bigint NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.user_comments OWNER TO fusionauth;

CREATE TABLE public.user_consents (
    id uuid NOT NULL,
    consents_id uuid NOT NULL,
    data text,
    giver_users_id uuid NOT NULL,
    insert_instant bigint NOT NULL,
    last_update_instant bigint NOT NULL,
    users_id uuid NOT NULL
);


ALTER TABLE public.user_consents OWNER TO fusionauth;


CREATE TABLE public.user_consents_email_plus (
    id bigint NOT NULL,
    next_email_instant bigint NOT NULL,
    user_consents_id uuid NOT NULL
);


ALTER TABLE public.user_consents_email_plus OWNER TO fusionauth;


CREATE SEQUENCE public.user_consents_email_plus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_consents_email_plus_id_seq OWNER TO fusionauth;


ALTER SEQUENCE public.user_consents_email_plus_id_seq OWNED BY public.user_consents_email_plus.id;



CREATE TABLE public.user_registrations (
    id uuid NOT NULL,
    applications_id uuid NOT NULL,
    authentication_token character varying(255),
    clean_speak_id uuid,
    data text,
    insert_instant bigint NOT NULL,
    last_login_instant bigint,
    timezone character varying(255),
    username character varying(191),
    username_status smallint NOT NULL,
    users_id uuid NOT NULL,
    verified boolean NOT NULL
);


ALTER TABLE public.user_registrations OWNER TO fusionauth;

CREATE TABLE public.user_registrations_application_roles (
    application_roles_id uuid NOT NULL,
    user_registrations_id uuid NOT NULL
);


ALTER TABLE public.user_registrations_application_roles OWNER TO fusionauth;


CREATE TABLE public.users (
    id uuid NOT NULL,
    active boolean NOT NULL,
    birth_date character(10),
    clean_speak_id uuid,
    data text,
    expiry bigint,
    first_name character varying(255),
    full_name character varying(255),
    image_url text,
    insert_instant bigint NOT NULL,
    last_name character varying(255),
    middle_name character varying(255),
    mobile_phone character varying(255),
    parent_email character varying(255),
    tenants_id uuid NOT NULL,
    timezone character varying(255)
);


ALTER TABLE public.users OWNER TO fusionauth;


CREATE TABLE public.version (
    version character varying(255) NOT NULL
);


ALTER TABLE public.version OWNER TO fusionauth;


CREATE TABLE public.webhooks (
    id uuid NOT NULL,
    connect_timeout integer NOT NULL,
    description character varying(255),
    data text,
    global boolean NOT NULL,
    headers text,
    http_authentication_username character varying(255),
    http_authentication_password character varying(255),
    read_timeout integer NOT NULL,
    ssl_certificate text,
    url text NOT NULL
);


ALTER TABLE public.webhooks OWNER TO fusionauth;


CREATE TABLE public.webhooks_applications (
    webhooks_id uuid NOT NULL,
    applications_id uuid NOT NULL
);


ALTER TABLE public.webhooks_applications OWNER TO fusionauth;


ALTER TABLE ONLY public.audit_logs ALTER COLUMN id SET DEFAULT nextval('public.audit_logs_id_seq'::regclass);



ALTER TABLE ONLY public.event_logs ALTER COLUMN id SET DEFAULT nextval('public.event_logs_id_seq'::regclass);


ALTER TABLE ONLY public.identities ALTER COLUMN id SET DEFAULT nextval('public.identities_id_seq'::regclass);



ALTER TABLE ONLY public.user_consents_email_plus ALTER COLUMN id SET DEFAULT nextval('public.user_consents_email_plus_id_seq'::regclass);



COPY public.application_daily_active_users (applications_id, count, day) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	18399
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	18398
3c219e58-ed0e-4b18-ad48-f4f92793ae32	8	18400
3c219e58-ed0e-4b18-ad48-f4f92793ae32	6	18401
3c219e58-ed0e-4b18-ad48-f4f92793ae32	10	18402
\.




COPY public.application_monthly_active_users (applications_id, count, month) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	21	604
\.




COPY public.application_registration_counts (applications_id, count, decremented_count, hour) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	0	441573
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	0	441614
3c219e58-ed0e-4b18-ad48-f4f92793ae32	4	0	441616
3c219e58-ed0e-4b18-ad48-f4f92793ae32	2	0	441629
3c219e58-ed0e-4b18-ad48-f4f92793ae32	2	0	441631
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	0	441640
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	0	441641
3c219e58-ed0e-4b18-ad48-f4f92793ae32	3	0	441657
3c219e58-ed0e-4b18-ad48-f4f92793ae32	2	0	441658
3c219e58-ed0e-4b18-ad48-f4f92793ae32	4	0	441659
\.




COPY public.application_roles (id, applications_id, description, is_default, is_super_role, name) FROM stdin;
631ecd9d-8d40-4c13-8277-80cedb8236e2	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Global admin	f	t	admin
631ecd9d-8d40-4c13-8277-80cedb8236e3	3c219e58-ed0e-4b18-ad48-f4f92793ae32	API key manager	f	f	api_key_manager
631ecd9d-8d40-4c13-8277-80cedb8236e4	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Application deleter	f	f	application_deleter
631ecd9d-8d40-4c13-8277-80cedb8236e5	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Application manager	f	f	application_manager
631ecd9d-8d40-4c13-8277-80cedb8236e6	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Audit log viewer	f	f	audit_log_viewer
631ecd9d-8d40-4c13-8277-80cedb8236e7	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Email template manager	f	f	email_template_manager
631ecd9d-8d40-4c13-8277-80cedb8236e8	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Report viewer	f	f	report_viewer
631ecd9d-8d40-4c13-8277-80cedb8236e9	3c219e58-ed0e-4b18-ad48-f4f92793ae32	System configuration manager	f	f	system_manager
631ecd9d-8d40-4c13-8277-80cedb8236f0	3c219e58-ed0e-4b18-ad48-f4f92793ae32	User action deleter	f	f	user_action_deleter
631ecd9d-8d40-4c13-8277-80cedb8236f1	3c219e58-ed0e-4b18-ad48-f4f92793ae32	User action manager	f	f	user_action_manager
631ecd9d-8d40-4c13-8277-80cedb8236f2	3c219e58-ed0e-4b18-ad48-f4f92793ae32	User deleter	f	f	user_deleter
631ecd9d-8d40-4c13-8277-80cedb8236f3	3c219e58-ed0e-4b18-ad48-f4f92793ae32	User manager	f	f	user_manager
631ecd9d-8d40-4c13-8277-80cedb8236f4	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Webhook manager	f	f	webhook_manager
631ecd9d-8d40-4c13-8277-80cedb8236f5	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Group manager	f	f	group_manager
631ecd9d-8d40-4c13-8277-80cedb8236f6	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Group deleter	f	f	group_deleter
631ecd9d-8d40-4c13-8277-80cedb8236f7	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Tenant manager	f	f	tenant_manager
631ecd9d-8d40-4c13-8277-80cedb8236f8	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Tenant deleter	f	f	tenant_deleter
631ecd9d-8d40-4c13-8277-80cedb8236f9	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Lambda manager	f	f	lambda_manager
631ecd9d-8d40-4c13-8277-80cedb8236fa	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Event log viewer	f	f	event_log_viewer
631ecd9d-8d40-4c13-8277-80cedb8236fb	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Key manager	f	f	key_manager
631ecd9d-8d40-4c13-8277-80cedb8236fc	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Consent deleter	f	f	consent_deleter
631ecd9d-8d40-4c13-8277-80cedb8236fd	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Consent manager	f	f	consent_manager
631ecd9d-8d40-4c13-8277-80cedb8236fe	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Theme manager	f	f	theme_manager
631ecd9d-8d40-4c13-8277-80cedb8236ff	3c219e58-ed0e-4b18-ad48-f4f92793ae32	Reactor manager	f	f	reactor_manager
\.




COPY public.applications (id, access_token_populate_lambdas_id, access_token_signing_keys_id, active, data, id_token_populate_lambdas_id, id_token_signing_keys_id, name, samlv2_issuer, samlv2_keys_id, samlv2_populate_lambdas_id, tenants_id, verification_email_templates_id) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	t	{"oauthConfiguration": {"authorizedRedirectURLs": ["/login"], "clientId": "3c219e58-ed0e-4b18-ad48-f4f92793ae32", "clientSecret": "MzJmNjMyODIxMmQ5MTIzZTQ1MDAzOGJiMTFiNDUxM2YyMzdiMTNjODhmYzk0NDE5YmE0OGM4MTdjZGI0MjlmNQ==", "enabledGrants": ["authorization_code"], "logoutURL": "/", "generateRefreshTokens": false, "requireClientAuthentication": true}, "loginConfiguration": {"allowTokenRefresh": false, "generateRefreshTokens": false, "requireAuthentication": true}}	\N	\N	FusionAuth	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
\.




COPY public.audit_logs (id, insert_instant, insert_user, message, data) FROM stdin;
1	1589726409128	vadim.shtukan@gmail.com	Created the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"reason":"FusionAuth User Interface"}
2	1589728777252	vadim.shtukan@gmail.com	Updated the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"newValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"oldValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"reason":"FusionAuth User Interface"}
3	1589739781742	vadim.shtukan@gmail.com	Updated the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"newValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"oldValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"reason":"FusionAuth User Interface"}
4	1589739980479	vadim.shtukan@gmail.com	Updated the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"newValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"oldValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"reason":"FusionAuth User Interface"}
5	1589740028297	vadim.shtukan@gmail.com	Created the group with Id [34ecf9ee-1a82-4c22-9d46-f84e118455b7] and name [Test]	{"data":{},"reason":"FusionAuth User Interface"}
6	1589740067744	vadim.shtukan@gmail.com	Updated the group with Id [34ecf9ee-1a82-4c22-9d46-f84e118455b7] and name [Test]	{"data":{},"newValue":{"id":"34ecf9ee-1a82-4c22-9d46-f84e118455b7","name":"Test","roles":{"3c219e58-ed0e-4b18-ad48-f4f92793ae32":[{"description":"Global admin","id":"631ecd9d-8d40-4c13-8277-80cedb8236e2","isDefault":false,"isSuperRole":true,"name":"admin"}]},"tenantId":"9241a956-aead-abfc-c78c-d3c1cd5ade42"},"oldValue":{"id":"34ecf9ee-1a82-4c22-9d46-f84e118455b7","name":"Test","roles":{"3c219e58-ed0e-4b18-ad48-f4f92793ae32":[{"description":"Global admin","id":"631ecd9d-8d40-4c13-8277-80cedb8236e2","isDefault":false,"isSuperRole":true,"name":"admin"}]},"tenantId":"9241a956-aead-abfc-c78c-d3c1cd5ade42"},"reason":"FusionAuth User Interface"}
7	1589867369973	vadim.shtukan@gmail.com	Updated the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"newValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/login":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"oldValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"reason":"FusionAuth User Interface"}
8	1589867489907	vadim.shtukan@gmail.com	Updated the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"newValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/login":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"oldValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/login":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"reason":"FusionAuth User Interface"}
9	1589881267004	vadim.shtukan@gmail.com	Updated the API authentication key [vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM]	{"data":{},"newValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/login":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"oldValue":{"id":"vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM","metaData":{},"permissions":{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/login":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}},"reason":"FusionAuth User Interface"}
\.



COPY public.authentication_keys (id, permissions, meta_data, tenants_id) FROM stdin;
__internal_YjgzM2E3ODJiMmRmNTA4NmI4ZjAzYmM4MWE0YTczNDQ1MGQ2MGRjNGIxZjExNDM5ZTk1MzU4NTg0ZjI1NTRlOA==	{"endpoints": {"/api/cache/reload": ["POST"], "/api/system/log/export": ["POST"]}}	{"attributes": {"internalCacheReloader": "true", "internalLogDownloader": "true"}}	\N
vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM	{"endpoints":{"/api/application/oauth-configuration":["POST","GET"],"/api/application/role":["DELETE","POST","GET","PUT","PATCH"],"/api/cache/reload":["DELETE","POST","GET","PUT","PATCH"],"/api/cleanspeak/notify":["DELETE","POST","GET","PUT","PATCH"],"/api/consent":["DELETE","POST","GET","PUT","PATCH"],"/api/group":["DELETE","POST","GET","PUT","PATCH"],"/api/group/member":["DELETE","POST","GET","PUT","PATCH"],"/api/login":["DELETE","POST","GET","PUT","PATCH"],"/api/system-configuration":["DELETE","POST","GET","PUT","PATCH"],"/api/user":["DELETE","POST","GET","PUT","PATCH"],"/api/user/registration":["DELETE","POST","GET","PUT","PATCH"]}}	{}	\N
\.



COPY public.breached_password_metrics (tenants_id, matched_exact_count, matched_sub_address_count, matched_common_password_count, matched_password_count, passwords_checked_count) FROM stdin;
\.




COPY public.clean_speak_applications (applications_id, clean_speak_application_id) FROM stdin;
\.




COPY public.common_breached_passwords (password) FROM stdin;
\.




COPY public.consents (id, consent_email_templates_id, data, name, email_plus_email_templates_id) FROM stdin;
46b6b789-8fc8-4e63-bae1-cd3a1e1c5c3c	1695e4e6-9483-4d9b-b615-f05fed5860e2	{"countryMinimumAgeForSelfConsent":{},"data":{},"defaultMinimumAgeForSelfConsent":13,"emailPlus":{"enabled":false,"maximumTimeToSendEmailInHours":48,"minimumTimeToSendEmailInHours":24},"multipleValuesAllowed":false,"values":[]}	COPPA VPC	\N
f5fee5d1-58f6-482e-bb52-1586dbb730d3	1695e4e6-9483-4d9b-b615-f05fed5860e2	{"countryMinimumAgeForSelfConsent":{},"data":{},"defaultMinimumAgeForSelfConsent":13,"emailPlus":{"enabled":true,"maximumTimeToSendEmailInHours":48,"minimumTimeToSendEmailInHours":24},"multipleValuesAllowed":false,"values":[]}	COPPA Email+	0adfa726-7e23-499d-81cc-e5e84274b6bd
\.




COPY public.data_sets (name, last_update_instant) FROM stdin;
BreachPasswords	1581476456155
\.



COPY public.email_templates (id, default_from_name, default_html_template, default_subject, default_text_template, from_email, localized_from_names, localized_html_templates, localized_subjects, localized_text_templates, name) FROM stdin;
eade5617-bc8c-4abb-807e-4e3e047656fe	\N	[#setting url_escaping_charset="UTF-8"]\nTo change your password click on the following link.\n<p>\n  [#-- The optional 'state' map provided on the Forgot Password API call is exposed in the template as 'state' --]\n  [#assign url = "http://localhost:9011/password/change/${changePasswordId}?tenantId=${user.tenantId}" /]\n  [#list state!{} as key, value][#if key != "tenantId" && value??][#assign url = url + "&" + key?url + "=" + value?url/][/#if][/#list]\n  <a href="${url}">${url}</a>\n</p>\n- FusionAuth Admin\n	Reset your password	[#setting url_escaping_charset="UTF-8"]\nTo change your password click on the following link.\n\n[#-- The optional 'state' map provided on the Forgot Password API call is exposed in the template as 'state' --]\n[#assign url = "http://localhost:9011/password/change/${changePasswordId}?tenantId=${user.tenantId}" /]\n[#list state!{} as key, value][#if key != "tenantId" && value??][#assign url = url + "&" + key?url + "=" + value?url/][/#if][/#list]\n\n${url}\n\n- FusionAuth Admin\n	\N	{}	{}	{}	{}	Forgot Password
86c15194-496e-48d5-9099-2554b11cab22	\N	[#setting url_escaping_charset="UTF-8"]\nYou have requested to log into FusionAuth using this email address. If you do not recognize this request please ignore this email.\n<p>\n  [#-- The optional 'state' map provided on the Start Passwordless API call is exposed in the template as 'state' --]\n  [#assign url = "http://localhost:9011/oauth2/passwordless/${code}?tenantId=${user.tenantId}" /]\n  [#list state!{} as key, value][#if key != "tenantId" && value??][#assign url = url + "&" + key?url + "=" + value?url/][/#if][/#list]\n  <a href="${url}">${url}</a>\n</p>\n- FusionAuth Admin\n	Log into FusionAuth	[#setting url_escaping_charset="UTF-8"]\nYou have requested to log into FusionAuth using this email address. If you do not recognize this request please ignore this email.\n\n[#-- The optional 'state' map provided on the Start Passwordless API call is exposed in the template as 'state' --]\n[#assign url = "http://localhost:9011/oauth2/passwordless/${code}?tenantId=${user.tenantId}" /]\n[#list state!{} as key, value][#if key != "tenantId" && value??][#assign url = url + "&" + key?url + "=" + value?url/][/#if][/#list]\n\n${url}\n\n- FusionAuth Admin\n	\N	{}	{}	{}	{}	Passwordless Login
c264114d-e63d-415f-bfa9-02f549c92cf1	\N	Your account has been created and you must setup a password. Click on the following link to setup your password.\n<p>\n  <a href="http://localhost:9011/password/change/${changePasswordId}?tenantId=${user.tenantId}">\n    http://localhost:9011/password/change/${changePasswordId}?tenantId=${user.tenantId}\n  </a>\n</p>\n- FusionAuth Admin	Setup your password	Your account has been created and you must setup a password. Click on the following link to setup your password.\n\nhttp://localhost:9011/password/change/${changePasswordId}?tenantId=${user.tenantId}\n\n- FusionAuth Admin	\N	{}	{}	{}	{}	Setup Password
1f60ed4f-dd9b-4ed8-b048-3705c838785b	\N	[#if user.verified]\nPro tip, your email has already been verified, but feel free to complete the verification process to verify your verification of your email address.\n[/#if]\n\nTo complete your email verification click on the following link.\n<p>\n  <a href="http://localhost:9011/email/verify/${verificationId}?tenantId=${user.tenantId}">\n    http://localhost:9011/email/verify/${verificationId}?tenantId=${user.tenantId}\n  </a>\n</p>\n\n- FusionAuth Admin	Verify your FusionAuth email address	[#if user.verified]\nPro tip, your email has already been verified, but feel free to complete the verification process to verify your verification of your email address.\n[/#if]\n\nTo complete your email verification click on the following link.\n\nhttp://localhost:9011/email/verify/${verificationId}?tenantId=${user.tenantId}\n\n- FusionAuth Admin	\N	{}	{}	{}	{}	Email Verification
b506246d-ca7c-48ab-8b34-3339d99c791a	\N	<p>This password was found in the list of vulnerable passwords, and is no longer secure.</p>\n\n<p>In order to secure your account, it is recommended to change your password at your earliest convenience.</p>\n\n<p>Follow this link to change your password.</p>\n\n<a href="http://localhost:9011/password/forgot?email=${user.email}&tenantId=${user.tenantId}">\n  http://localhost:9011/password/forgot?email=${user.email}&tenantId=${user.tenantId}\n</a>\n\n- FusionAuth Admin	Your password is not secure	This password was found in the list of vulnerable passwords, and is no longer secure.\n\nIn order to secure your account, it is recommended to change your password at your earliest convenience.\n\nFollow this link to change your password.\n\nhttp://localhost:9011/password/forgot?email=${user.email}&tenantId=${user.tenantId}\n\n- FusionAuth Admin\n\n\n	\N	{}	{}	{}	{}	Breached Password Notification
0275645d-b277-480a-8d00-0319e2670ef1	\N	[#if registration.verified]\nPro tip, your registration has already been verified, but feel free to complete the verification process to verify your verification of your registration.\n[/#if]\n\nTo complete your registration verification click on the following link.\n<p>\n  <a href="http://localhost:9011/registration/verify/${verificationId}?tenantId=${user.tenantId}">\n    http://localhost:9011/registration/verify/${verificationId}?tenantId=${user.tenantId}\n  </a>\n</p>\n- FusionAuth Admin	Verify your registration	[#if registration.verified]\nPro tip, your registration has already been verified, but feel free to complete the verification process to verify your verification of your registration.\n[/#if]\n\nTo complete your registration verification click on the following link.\n\nhttp://localhost:9011/registration/verify/${verificationId}?tenantId=${user.tenantId}\n\n- FusionAuth Admin	\N	{}	{}	{}	{}	Registration Verification
1695e4e6-9483-4d9b-b615-f05fed5860e2	\N	You recently granted your child consent in our system. This email is to notify you of this consent. If you did not grant this consent or wish to revoke this consent, click the link below:\n<p>\n  <a href="http://example.com/consent/manage">http://example.com/consent/manage</a>\n</p>\n- FusionAuth Admin	Notice of your consent	You recently granted your child consent in our system. This email is to notify you of this consent. If you did not grant this consent or wish to revoke this consent, click the link below:\n\nhttp://example.com/consent/manage\n\n- FusionAuth Admin	\N	{}	{}	{}	{}	COPPA Notice
0adfa726-7e23-499d-81cc-e5e84274b6bd	\N	A while ago, you granted your child consent in our system. This email is a second notice of this consent as required by law and also to remind to that you can revoke this consent at anytime on our website or by clicking the link below:\n<p>\n  <a href="http://example.com/consent/manage">http://example.com/consent/manage</a>\n</p>\n- FusionAuth Admin	Reminder: Notice of your consent	A while ago, you granted your child consent in our system. This email is a second notice of this consent as required by law and also to remind to that you can revoke this consent at anytime on our website or by clicking the link below:\n\nhttp://example.com/consent/manage\n\n- FusionAuth Admin	\N	{}	{}	{}	{}	COPPA Notice Reminder
\.




COPY public.event_logs (id, insert_instant, message, type) FROM stdin;
1	1589665073641	Could not connect to SMTP host: localhost, port: 25\n\nCause: java.net.ConnectException\nMessage: Connection refused	2
\.




COPY public.external_identifiers (id, applications_id, data, insert_instant, tenants_id, type, users_id) FROM stdin;
\.



COPY public.failed_logins (count, last_failed_instant, tenants_id, users_id) FROM stdin;
\.




COPY public.families (data, family_id, insert_instant, owner, role, users_id) FROM stdin;
\.



COPY public.federated_domains (identity_providers_id, domain) FROM stdin;
\.



COPY public.global_daily_active_users (count, day) FROM stdin;
1	18399
1	18398
8	18400
6	18401
10	18402
\.



COPY public.global_monthly_active_users (count, month) FROM stdin;
21	604
\.


COPY public.global_registration_counts (count, decremented_count, hour) FROM stdin;
1	0	441573
1	0	441614
4	0	441616
2	0	441629
2	0	441631
1	0	441640
1	0	441641
3	0	441657
2	0	441658
4	0	441659
\.



COPY public.group_application_roles (application_roles_id, groups_id) FROM stdin;
\.



COPY public.group_members (id, groups_id, data, insert_instant, users_id) FROM stdin;
\.



COPY public.groups (id, data, name, tenants_id) FROM stdin;
34ecf9ee-1a82-4c22-9d46-f84e118455b7	{}	Test	9241a956-aead-abfc-c78c-d3c1cd5ade42
\.


COPY public.hourly_logins (applications_id, count, data, hour) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	\N	441573
3c219e58-ed0e-4b18-ad48-f4f92793ae32	2	\N	441589
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	\N	441594
3c219e58-ed0e-4b18-ad48-f4f92793ae32	2	\N	441614
3c219e58-ed0e-4b18-ad48-f4f92793ae32	4	\N	441616
3c219e58-ed0e-4b18-ad48-f4f92793ae32	4	\N	441629
3c219e58-ed0e-4b18-ad48-f4f92793ae32	9	\N	441631
3c219e58-ed0e-4b18-ad48-f4f92793ae32	3	\N	441633
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	\N	441634
3c219e58-ed0e-4b18-ad48-f4f92793ae32	5	\N	441635
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1	\N	441636
3c219e58-ed0e-4b18-ad48-f4f92793ae32	5	\N	441637
3c219e58-ed0e-4b18-ad48-f4f92793ae32	4	\N	441640
3c219e58-ed0e-4b18-ad48-f4f92793ae32	6	\N	441641
3c219e58-ed0e-4b18-ad48-f4f92793ae32	11	\N	441657
3c219e58-ed0e-4b18-ad48-f4f92793ae32	4	\N	441658
3c219e58-ed0e-4b18-ad48-f4f92793ae32	6	\N	441659
\.



COPY public.identities (id, breached_password_last_checked_instant, breached_password_status, email, encryption_scheme, factor, last_login_instant, password, password_change_reason, password_change_required, password_last_update_instant, salt, status, tenants_id, two_factor_delivery, two_factor_enabled, two_factor_secret, username, username_index, username_status, users_id, verified) FROM stdin;
9	\N	\N	josefa.schoen19@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589873716841	LA/KO9eCfLOUTnFLoEoodDS5oFPWpLZRpRQwfxC4jkQ=	\N	f	1589873716830	vvCr9b5I9IoWNqhzujBLkaz4rfw2MUElv2T6DjFqSRk=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Josiah95	JOSIAH95	0	6d2e5741-f635-4b9b-8df1-0c9e5768ef36	t
10	\N	\N	changedbestjohn@doe.com	salted-pbkdf2-hmac-sha256	24000	\N	T+AuyoeZEi4Lh55Tvp5lVONkeUYIOUFD7a+oS/ItVU0=	\N	f	1589873718429	rw1XUCm9cbpX3qG+wzZAhrKYqyvLtnMT8lGqPbe7S3M=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	CHANGED1 kkk	CHANGED1 KKK	0	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8	t
2	\N	\N	example@fusionauth.io	salted-pbkdf2-hmac-sha256	24000	1589812264623	jU0Qgexg1dO5fIv8eIsTfaQXGVL8+YdrAmw6rrWyniQ=	\N	f	1589812264589	iPPXdGXZv0Rc7zxPDLVCf75eKpq1xN9lfY7A0c1hT+I=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	johnny123	JOHNNY123	0	1b656512-68fc-4fa2-b2b9-5a4baef56f06	t
3	\N	\N	bestjohn@doe.com	salted-pbkdf2-hmac-sha256	24000	1589819209225	oLiYBG03JTEstGzbGR+3qQivSEUVi/4PKWdsgO3/W4w=	\N	f	1589819209213	p2GHxSsXA80Wb0gb8SVBiJYVEfoF4lOBbHX8ovoeTMM=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	kkk	KKK	0	389d0689-3f4f-4b49-96f9-c476a2b08c98	t
4	\N	\N	cicero29@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589819821710	0Lr/XaS7iCcMt52GJhF4645a3BuVU6HrnijY2697wlM=	\N	f	1589819821695	m+k7k3n0P4FMkzjZ/t5GdIZvu5QyeYddtPVfd1xpTvc=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Nedra.Larkin47	NEDRA.LARKIN47	0	448f52cb-220c-4c49-b548-eb434106879c	t
5	\N	\N	ruthe16@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589819833968	Pac8+unMhRegJnoKYjRqkKxem7AR0o/Xi73Rbnn0DIk=	\N	f	1589819833960	rmsUT0PIhkEGz0xwlUpHGo8ZGEMV+J5Z6qCfIihvSDE=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Gladys.Haag	GLADYS.HAAG	0	3fb27572-c561-4da5-8f83-527d370ea455	t
6	\N	\N	sallie_emard@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589819875007	9wfELhYKqmHNWqxd+Z2Zu36s39Uwia9heiOcznEDYDw=	\N	f	1589819874996	prXQNBj4/268Ax21+BlPbrfL0lsJAonMl2w+Gg0eSSM=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Zetta62	ZETTA62	0	d0620065-170b-4d66-92a1-59d657534dd0	t
115	\N	\N	glenda3@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589974020517	M5Zo4iMT+5t8ykRo9ASAxHflgcT0y1GcwtzlGbDhOTc=	\N	f	1589974020504	uUoYm8/ekdZo0JQPbLtHW5Xs5C97gdh2wetODzdzRYY=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Jayme6	JAYME6	0	76789ec2-79a6-4c37-ba00-0fda4b369e96	t
7	\N	\N	toy64@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589867432188	Yc2TZs8kNo6w6B9eshZyWHBgE4r1Irq3YfVLKuGbUGQ=	\N	f	1589867432169	sgc0n2bPHF89vcfpxqVNU5gowIaWAuYKnbySPDx1Tio=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Joan_Prosacco42	JOAN_PROSACCO42	0	23ebf7a3-f3a9-4f1f-af9e-f883a7f30c2c	t
1	\N	\N	vadim.shtukan@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589972402205	nFRAsH6wOmgnV9Llkl/T2Tdbj5k/JY9p2i+cxEer/6Q=	\N	f	1589663506241	jcqRAv++LYgvgVb+Lp1IrK+10SFCpOzGCW7K7+DxSo8=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	\N	\N	0	8c59d7a6-a239-42d0-8886-5abe8545e0dd	t
81	\N	\N	changedbestjohn@doe.com1	salted-pbkdf2-hmac-sha256	24000	\N	eWbESfKdb9RUJEhpn+9EJu0dCBc40z5aTVSM26mHKCw=	\N	f	1589972084670	kV1PEKnCV41d6vZFcndOdbX+zikQPIvmdhs9cm8fQs0=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	CHANGED1 kkk1	CHANGED1 KKK1	0	748c95bd-8108-4eee-bb1b-f2f74dfa339f	t
45	\N	\N	lucio.wuckert17@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589967023319	VFDrjj0H+yT6Mv8VePUl1Wmsm+WDd+h84AKSlFzxZx4=	\N	f	1589966896622	n8b+8a4cP0xGtET3KehCuYPmsCczdWvqlmMndnaSRMw=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Christopher.Kiehn	CHRISTOPHER.KIEHN	0	ec515da7-5a9e-463e-934b-b156265f459a	t
11	\N	\N	melba_buckridge@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589909972225	5pF/8j9nQDsydmoni7KvQbDGWwSM11XYB+T5bjYKFJM=	\N	f	1589905871290	hFgkRiernBQpnGOyb7JFP6vFNZ4tF2pETnDg18gzQA4=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Gerry64	GERRY64	0	ccfa3a5c-4e32-474e-9208-cedfcf1885ef	t
8	\N	\N	geovanni.haag@yahoo.com	salted-pbkdf2-hmac-sha256	24000	1589873712767	3vLGuTOzgErtYCibzw9CTCpNorsm5VDS5gS8INH76vs=	\N	f	1589867602384	hqFtG1/BBQOh95PVFK5K+6nNtBQLwB36wcEr24MTRJk=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Davonte_Wolf87	DAVONTE_WOLF87	0	5b7ee3bd-53c2-4328-ac35-8113c5cb7486	t
12	\N	\N	renee.price@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589910002326	z7BFgjecwgSQu0XBFZfc8NtAHGVi5xS5mrtg7eqx/w8=	\N	f	1589910000365	mu6yLVDB89jPYJtUIkpCRq0178Zlei6YgrG89O/mQNo=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Lilla.Fadel40	LILLA.FADEL40	0	9c77333a-8770-4728-a171-313747d93285	t
46	\N	\N	ava.goldner54@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589967465505	vM998tbyBeGL/Qz5jh2tRcFoFKbyy0STINmYxzqeWYI=	\N	f	1589967462411	rd7UZVSp0tsCM9XaNHJCV58OAM94RkHmnLbE0RRVTps=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Cloyd21	CLOYD21	0	fa4266c2-f2f3-43d2-93c5-771e3cff42bd	t
47	\N	\N	josianne_hackett@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589967854084	TBJe2Kk1Q7Z/B3U83r6+LFkuT3gstU5CQ0wtnvGj0Hk=	\N	f	1589967854069	l4pWEocOa7f5wOImWtNDjoCLBG6S6r0ensPmjJ6qQYI=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Mohammed_Klein38	MOHAMMED_KLEIN38	0	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd	t
116	\N	\N	elwin.breitenberg@hotmail.com	salted-pbkdf2-hmac-sha256	24000	1589974029097	NKqjzneLwtSTSrfQsGx7s6CJ2nR8T23xKBz5RM51mhE=	\N	f	1589974029086	qguvT91Fvq5RTGYauy5Oe4A5J5TeVhlT0bLT2zIbRrA=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Willard88	WILLARD88	0	61e27900-437f-4b4f-90bf-b56640cd1aeb	t
80	\N	\N	brooklyn.cartwright45@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589971971063	7R2RbA3A0cKQQ4h/uwHSgBXJOrO9AFSEQme5ps/7hoI=	\N	f	1589971971042	sgi+YdFf0TLFapa2jMJBeK87efBMRjpe/DO9TcH6SZ8=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Ola_Hilll51	OLA_HILLL51	0	b03e6ee1-2524-4c3c-9985-005a9a4afbb9	t
117	\N	\N	ellsworth71@gmail.com	salted-pbkdf2-hmac-sha256	24000	1589974030854	ocAZCyFkaNPuGxmNrBLtVvqhIaCN+nEwN+Dru5ALTro=	\N	f	1589974030844	qx0XEu7CQ9M/Wov1NPRMc4eFfqjK7PAztOPx5amfRJc=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	Elise40	ELISE40	0	b02d1a23-af36-40e4-b5e0-c8a605a14b93	t
114	\N	\N	changedbestjohn1@doe.com1	salted-pbkdf2-hmac-sha256	24000	\N	O8FldfIVhz0bpFgL4RNbU7QNT75A0gWcKNfwscGPbUM=	\N	f	1589972439653	twGBmRUW8yfnSxWAvJ5E3qh6PnYkTZXWi+mPMuXfR8M=	0	9241a956-aead-abfc-c78c-d3c1cd5ade42	0	f	\N	CHANGED1 kkk12	CHANGED1 KKK12	0	529bd8a3-3aab-4907-81e0-c6b155881186	t
\.



COPY public.identity_providers (id, data, enabled, name, type, keys_id, reconcile_lambdas_id) FROM stdin;
\.



COPY public.identity_providers_applications (applications_id, identity_providers_id, data, enabled) FROM stdin;
\.



COPY public.instance (id, encryption_key, license_id) FROM stdin;
fd2025b4-89f1-9273-db2f-007e25aa4027	\N	\N
\.



COPY public.integrations (data) FROM stdin;
{}
\.



COPY public.keys (id, algorithm, certificate, expiration_instant, insert_instant, issuer, kid, name, private_key, public_key, secret, type) FROM stdin;
8aa046f2-675e-a31f-53f0-bf7fbcd273b1	HS256	\N	\N	1589663448330	\N	c300189a6	Default signing key	\N	\N	Tf7uQRY3zJ2YKYdf5Mj9ZlqgtA3j+wip6C6weJtm4c4=	HMAC
092dbedc-30af-4149-9c61-b578f2c72f59	HS256	\N	\N	1589663449330	\N	fdbccde48	OpenID Connect compliant HMAC using SHA-256	\N	\N	\N	HMAC
4b8f1c06-518e-45bd-9ac5-d549686ae02a	HS384	\N	\N	1589663450330	\N	c56d6e06e	OpenID Connect compliant HMAC using SHA-384	\N	\N	\N	HMAC
c753a44d-7f2e-48d3-bc4e-c2c16488a23b	HS512	\N	\N	1589663451330	\N	1c2f1954a	OpenID Connect compliant HMAC using SHA-512	\N	\N	\N	HMAC
\.




COPY public.lambdas (id, body, debug, enabled, insert_instant, name, type) FROM stdin;
\.



COPY public.locks (type, update_instant) FROM stdin;
UserActionEndEvent	\N
Family	\N
com.inversoft.migration.Migrator	\N
Reindex	\N
\.




COPY public.master_record (id, instant) FROM stdin;
fef7cc38-6de8-40b6-9230-811106a49eff	1589985005656
\.



COPY public.migrations (name, run_instant) FROM stdin;
io.fusionauth.api.migration.guice.Migration_1_6_0	1589663453994
io.fusionauth.api.migration.guice.Migration_1_7_0	1589663454006
io.fusionauth.api.migration.guice.Migration_1_8_0	1589663454034
io.fusionauth.api.migration.guice.Migration_1_9_2	1589663454046
io.fusionauth.api.migration.guice.Migration_1_10_0	1589663454061
io.fusionauth.api.migration.guice.Migration_1_13_0	1589663459076
io.fusionauth.api.migration.guice.Migration_1_15_3	1589663459129
\.



COPY public.nodes (id, insert_instant, last_checkin_instant, runtime_mode, url) FROM stdin;
fef7cc38-6de8-40b6-9230-811106a49eff	1589983271627	1589985051653	development	http://172.17.0.5:9011
\.



COPY public.previous_passwords (create_instant, encryption_scheme, factor, password, salt, users_id) FROM stdin;
\.




COPY public.raw_application_daily_active_users (applications_id, day, users_id) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18401	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18401	6d2e5741-f635-4b9b-8df1-0c9e5768ef36
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18401	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18401	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18401	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18401	9c77333a-8770-4728-a171-313747d93285
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	fa4266c2-f2f3-43d2-93c5-771e3cff42bd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	b03e6ee1-2524-4c3c-9985-005a9a4afbb9
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	748c95bd-8108-4eee-bb1b-f2f74dfa339f
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	529bd8a3-3aab-4907-81e0-c6b155881186
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	76789ec2-79a6-4c37-ba00-0fda4b369e96
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	61e27900-437f-4b4f-90bf-b56640cd1aeb
3c219e58-ed0e-4b18-ad48-f4f92793ae32	18402	b02d1a23-af36-40e4-b5e0-c8a605a14b93
\.



COPY public.raw_application_monthly_active_users (applications_id, month, users_id) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	1b656512-68fc-4fa2-b2b9-5a4baef56f06
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	389d0689-3f4f-4b49-96f9-c476a2b08c98
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	448f52cb-220c-4c49-b548-eb434106879c
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	3fb27572-c561-4da5-8f83-527d370ea455
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	d0620065-170b-4d66-92a1-59d657534dd0
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	23ebf7a3-f3a9-4f1f-af9e-f883a7f30c2c
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	6d2e5741-f635-4b9b-8df1-0c9e5768ef36
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	9c77333a-8770-4728-a171-313747d93285
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	fa4266c2-f2f3-43d2-93c5-771e3cff42bd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	b03e6ee1-2524-4c3c-9985-005a9a4afbb9
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	748c95bd-8108-4eee-bb1b-f2f74dfa339f
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	529bd8a3-3aab-4907-81e0-c6b155881186
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	76789ec2-79a6-4c37-ba00-0fda4b369e96
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	61e27900-437f-4b4f-90bf-b56640cd1aeb
3c219e58-ed0e-4b18-ad48-f4f92793ae32	604	b02d1a23-af36-40e4-b5e0-c8a605a14b93
\.



COPY public.raw_global_daily_active_users (day, users_id) FROM stdin;
18401	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
18401	6d2e5741-f635-4b9b-8df1-0c9e5768ef36
18401	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
18401	8c59d7a6-a239-42d0-8886-5abe8545e0dd
18401	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
18401	9c77333a-8770-4728-a171-313747d93285
18402	ec515da7-5a9e-463e-934b-b156265f459a
18402	fa4266c2-f2f3-43d2-93c5-771e3cff42bd
18402	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd
18402	8c59d7a6-a239-42d0-8886-5abe8545e0dd
18402	b03e6ee1-2524-4c3c-9985-005a9a4afbb9
18402	748c95bd-8108-4eee-bb1b-f2f74dfa339f
18402	529bd8a3-3aab-4907-81e0-c6b155881186
18402	76789ec2-79a6-4c37-ba00-0fda4b369e96
18402	61e27900-437f-4b4f-90bf-b56640cd1aeb
18402	b02d1a23-af36-40e4-b5e0-c8a605a14b93
\.


COPY public.raw_global_monthly_active_users (month, users_id) FROM stdin;
604	8c59d7a6-a239-42d0-8886-5abe8545e0dd
604	1b656512-68fc-4fa2-b2b9-5a4baef56f06
604	389d0689-3f4f-4b49-96f9-c476a2b08c98
604	448f52cb-220c-4c49-b548-eb434106879c
604	3fb27572-c561-4da5-8f83-527d370ea455
604	d0620065-170b-4d66-92a1-59d657534dd0
604	23ebf7a3-f3a9-4f1f-af9e-f883a7f30c2c
604	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
604	6d2e5741-f635-4b9b-8df1-0c9e5768ef36
604	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
604	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
604	9c77333a-8770-4728-a171-313747d93285
604	ec515da7-5a9e-463e-934b-b156265f459a
604	fa4266c2-f2f3-43d2-93c5-771e3cff42bd
604	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd
604	b03e6ee1-2524-4c3c-9985-005a9a4afbb9
604	748c95bd-8108-4eee-bb1b-f2f74dfa339f
604	529bd8a3-3aab-4907-81e0-c6b155881186
604	76789ec2-79a6-4c37-ba00-0fda4b369e96
604	61e27900-437f-4b4f-90bf-b56640cd1aeb
604	b02d1a23-af36-40e4-b5e0-c8a605a14b93
\.



COPY public.raw_logins (applications_id, instant, ip_address, users_id) FROM stdin;
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589665109125	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589723803707	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589723852721	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589739741420	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589812011773	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589812264623	\N	1b656512-68fc-4fa2-b2b9-5a4baef56f06
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589819209225	\N	389d0689-3f4f-4b49-96f9-c476a2b08c98
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589819821710	\N	448f52cb-220c-4c49-b548-eb434106879c
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589819833968	\N	3fb27572-c561-4da5-8f83-527d370ea455
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589819875007	\N	d0620065-170b-4d66-92a1-59d657534dd0
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589867318075	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589867432188	\N	23ebf7a3-f3a9-4f1f-af9e-f883a7f30c2c
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589867602401	\N	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589867611931	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873698960	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873706590	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873708458	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873709857	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873711522	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873712767	0:0:0:0:0:0:0:1	5b7ee3bd-53c2-4328-ac35-8113c5cb7486
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873716841	\N	6d2e5741-f635-4b9b-8df1-0c9e5768ef36
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873718438	\N	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589873721492	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589880558450	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589880804037	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589881680106	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589885656897	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589886128244	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589888018612	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589888919700	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589888963339	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589889289974	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589892991981	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589894879022	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589895211405	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589895641619	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589896319532	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589896364272	0:0:0:0:0:0:0:1	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589905871351	\N	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589905875414	0:0:0:0:0:0:0:1	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589905961363	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589906583694	0:0:0:0:0:0:0:1	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589907764791	0:0:0:0:0:0:0:1	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589908233232	0:0:0:0:0:0:0:1	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589909748574	0:0:0:0:0:0:0:1	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589909972225	0:0:0:0:0:0:0:1	ccfa3a5c-4e32-474e-9208-cedfcf1885ef
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589910000381	\N	9c77333a-8770-4728-a171-313747d93285
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589910002326	0:0:0:0:0:0:0:1	9c77333a-8770-4728-a171-313747d93285
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589966896687	\N	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967014316	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967017291	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967018493	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967019774	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967020967	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967022187	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967023319	0:0:0:0:0:0:0:1	ec515da7-5a9e-463e-934b-b156265f459a
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967462424	\N	fa4266c2-f2f3-43d2-93c5-771e3cff42bd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967465505	0:0:0:0:0:0:0:1	fa4266c2-f2f3-43d2-93c5-771e3cff42bd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589967854084	\N	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589971896743	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589971971063	\N	b03e6ee1-2524-4c3c-9985-005a9a4afbb9
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589972084684	\N	748c95bd-8108-4eee-bb1b-f2f74dfa339f
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589972088469	192.168.99.1,172.17.0.7	748c95bd-8108-4eee-bb1b-f2f74dfa339f
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589972402205	127.0.0.1	8c59d7a6-a239-42d0-8886-5abe8545e0dd
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589972439671	\N	529bd8a3-3aab-4907-81e0-c6b155881186
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589972443187	192.168.99.1,172.17.0.7	529bd8a3-3aab-4907-81e0-c6b155881186
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589974020517	\N	76789ec2-79a6-4c37-ba00-0fda4b369e96
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589974029097	\N	61e27900-437f-4b4f-90bf-b56640cd1aeb
3c219e58-ed0e-4b18-ad48-f4f92793ae32	1589974030854	\N	b02d1a23-af36-40e4-b5e0-c8a605a14b93
\.



COPY public.refresh_tokens (token, users_id, applications_id, insert_instant, start_instant, meta_data) FROM stdin;
\.


COPY public.system_configuration (data, report_timezone) FROM stdin;
{"auditLogConfiguration":{"delete":{"enabled":false}},"cookieEncryptionIV":"6xSeMti2WSII/wYe+lunYw==","cookieEncryptionKey":"35Luys+4fyBPmJSiYxZsPg==","corsConfiguration":{"allowCredentials":false,"allowedHeaders":["Accept","Access-Control-Request-Headers","Access-Control-Request-Method","Authorization","Content-Type","Last-Modified","Origin","X-FusionAuth-TenantId","X-Requested-With"],"allowedMethods":["GET","OPTIONS"],"enabled":true,"exposedHeaders":["Access-Control-Allow-Origin","Access-Control-Allow-Credentials"],"preflightMaxAgeInSeconds":1800},"data":{},"eventLogConfiguration":{"numberToRetain":10000},"loginRecordConfiguration":{"delete":{"enabled":false}},"uiConfiguration":{}}	America/Denver
\.



COPY public.tenants (id, access_token_signing_keys_id, confirm_child_email_template_id, data, failed_authentication_user_actions_id, family_request_email_template_id, forgot_password_email_templates_id, id_token_signing_keys_id, name, parent_registration_email_template_id, passwordless_email_templates_id, set_password_email_templates_id, themes_id, verification_email_templates_id) FROM stdin;
9241a956-aead-abfc-c78c-d3c1cd5ade42	8aa046f2-675e-a31f-53f0-bf7fbcd273b1	\N	{"configured":false,"data":{},"emailConfiguration":{"defaultFromEmail":"no-reply@fusionauth.io","host":"localhost","port":25,"verifyEmail":false,"verifyEmailWhenChanged":false},"eventConfiguration":{},"externalIdentifierConfiguration":{"authorizationGrantIdTimeToLiveInSeconds":30,"changePasswordIdGenerator":{"length":32,"type":"randomBytes"},"changePasswordIdTimeToLiveInSeconds":600,"deviceCodeTimeToLiveInSeconds":1800,"deviceUserCodeIdGenerator":{"length":6,"type":"randomAlphaNumeric"},"emailVerificationIdGenerator":{"length":32,"type":"randomBytes"},"emailVerificationIdTimeToLiveInSeconds":86400,"externalAuthenticationIdTimeToLiveInSeconds":300,"oneTimePasswordTimeToLiveInSeconds":60,"passwordlessLoginGenerator":{"length":32,"type":"randomBytes"},"passwordlessLoginTimeToLiveInSeconds":180,"registrationVerificationIdGenerator":{"length":32,"type":"randomBytes"},"registrationVerificationIdTimeToLiveInSeconds":86400,"setupPasswordIdGenerator":{"length":32,"type":"randomBytes"},"setupPasswordIdTimeToLiveInSeconds":86400,"twoFactorIdTimeToLiveInSeconds":300,"twoFactorTrustIdTimeToLiveInSeconds":2592000},"failedAuthenticationConfiguration":{"actionDuration":3,"actionDurationUnit":"MINUTES","resetCountInSeconds":60,"tooManyAttempts":5},"familyConfiguration":{"allowChildRegistrations":true,"deleteOrphanedAccounts":false,"deleteOrphanedAccountsDays":30,"enabled":false,"maximumChildAge":12,"minimumOwnerAge":21,"parentEmailRequired":false},"httpSessionMaxInactiveInterval":3600,"issuer":"acme.com","jwtConfiguration":{"enabled":true,"refreshTokenTimeToLiveInMinutes":43200,"timeToLiveInSeconds":3600},"maximumPasswordAge":{"days":180,"enabled":false},"minimumPasswordAge":{"enabled":false,"seconds":30},"passwordEncryptionConfiguration":{"encryptionScheme":"salted-pbkdf2-hmac-sha256","encryptionSchemeFactor":24000,"modifyEncryptionSchemeOnLogin":false},"passwordValidationRules":{"breachDetection":{"enabled":false,"notifyUserEmailTemplateId":"b506246d-ca7c-48ab-8b34-3339d99c791a"},"maxLength":256,"minLength":8,"rememberPreviousPasswords":{"count":0,"enabled":false},"requireMixedCase":false,"requireNonAlpha":false,"requireNumber":false,"validateOnLogin":false},"userDeletePolicy":{"unverified":{"enabled":false,"numberOfDaysToRetain":0}}}	\N	\N	eade5617-bc8c-4abb-807e-4e3e047656fe	092dbedc-30af-4149-9c61-b578f2c72f59	Default	\N	86c15194-496e-48d5-9099-2554b11cab22	c264114d-e63d-415f-bfa9-02f549c92cf1	75a068fd-e94b-451a-9aeb-3ddb9a3b5987	\N
\.



COPY public.themes (id, data, insert_instant, last_update_instant, name) FROM stdin;
75a068fd-e94b-451a-9aeb-3ddb9a3b5987	{}	1589663452330	1589663448330	FusionAuth
\.



COPY public.user_action_logs (id, actioner_users_id, actionee_users_id, comment, create_instant, email_user_on_end, end_event_sent, expiry, history, localized_name, localized_option, localized_reason, name, notify_user_on_end, option_name, reason, reason_code, user_actions_id) FROM stdin;
\.



COPY public.user_action_logs_applications (applications_id, user_action_logs_id) FROM stdin;
\.



COPY public.user_action_reasons (id, localized_texts, text, code) FROM stdin;
\.


COPY public.user_actions (id, active, cancel_email_templates_id, end_email_templates_id, include_email_in_event_json, localized_names, modify_email_templates_id, name, options, prevent_login, send_end_event, start_email_templates_id, temporal, transaction_type, user_notifications_enabled, user_emailing_enabled) FROM stdin;
\.



COPY public.user_comments (id, comment, commenter_id, create_instant, users_id) FROM stdin;
\.



COPY public.user_consents (id, consents_id, data, giver_users_id, insert_instant, last_update_instant, users_id) FROM stdin;
\.



COPY public.user_consents_email_plus (id, next_email_instant, user_consents_id) FROM stdin;
\.



COPY public.user_registrations (id, applications_id, authentication_token, clean_speak_id, data, insert_instant, last_login_instant, timezone, username, username_status, users_id, verified) FROM stdin;
2630d0d9-44cf-4917-b112-c1d9e8928e14	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589873718436	1589896364272	\N	Ruthie20	0	0f4577e7-0c2e-4f2e-90b9-416386ec5cf8	t
ec44a0d8-1fd7-427e-8039-234801de0fba	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589972439664	1589972443187	\N	Carey38	0	529bd8a3-3aab-4907-81e0-c6b155881186	t
e521888c-29e8-43b1-ae2c-b295b3ac13e7	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589812264608	1589812264623	\N	johnny123	0	1b656512-68fc-4fa2-b2b9-5a4baef56f06	t
012971c2-7f67-4c8a-b350-36a585ff7c7c	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589819209221	1589819209225	\N	kkk	0	389d0689-3f4f-4b49-96f9-c476a2b08c98	t
ad80a2f6-7ebf-4dbe-99d7-b8f88c532511	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589819821706	1589819821710	\N	Nedra.Larkin47	0	448f52cb-220c-4c49-b548-eb434106879c	t
a5ff6d06-51ed-4185-b604-144e5ca5cb39	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589819833965	1589819833968	\N	Gladys.Haag	0	3fb27572-c561-4da5-8f83-527d370ea455	t
466d0cf3-6182-49b5-97eb-00049daa497d	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589819875004	1589819875007	\N	Zetta62	0	d0620065-170b-4d66-92a1-59d657534dd0	t
a2065fe4-e169-4997-a877-6baa0044f53c	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589867432179	1589867432188	\N	Joan_Prosacco42	0	23ebf7a3-f3a9-4f1f-af9e-f883a7f30c2c	t
92ab932f-ca63-45d1-baf1-399aa3156bfe	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589974020513	1589974020517	\N	Jayme6	0	76789ec2-79a6-4c37-ba00-0fda4b369e96	t
44c76d34-0af5-4dc9-8388-949b6fefd4ac	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589974029093	1589974029097	\N	Willard88	0	61e27900-437f-4b4f-90bf-b56640cd1aeb	t
3eb15f73-74d8-437e-aa30-f3e37282318d	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589905871338	1589909972225	\N	Gerry64	0	ccfa3a5c-4e32-474e-9208-cedfcf1885ef	t
25194e7e-87dd-43e3-9ba3-be6da897b5dd	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589974030851	1589974030854	\N	Elise40	0	b02d1a23-af36-40e4-b5e0-c8a605a14b93	t
7eec2b3c-7ef3-4813-807f-0ebd4615d8c3	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589867602394	1589873712767	\N	Davonte_Wolf87	0	5b7ee3bd-53c2-4328-ac35-8113c5cb7486	t
4b24f435-2b74-4533-8063-fd92c7704383	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589873716838	1589873716841	\N	Josiah95	0	6d2e5741-f635-4b9b-8df1-0c9e5768ef36	t
6ee33506-2798-4dc9-b25c-c9bbb791a40c	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589910000373	1589910002326	\N	Lilla.Fadel40	0	9c77333a-8770-4728-a171-313747d93285	t
90a05f8d-9f0b-4167-9208-a4c399543943	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589966896675	1589967023319	\N	Christopher.Kiehn	0	ec515da7-5a9e-463e-934b-b156265f459a	t
32b66f0d-c7e6-438c-954b-415e79cb6f7c	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589967462420	1589967465505	\N	Cloyd21	0	fa4266c2-f2f3-43d2-93c5-771e3cff42bd	t
06bc7372-aea9-4b60-ad6f-1fd08bf27d4d	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589967854080	1589967854084	\N	Mohammed_Klein38	0	c2a88b3b-3a72-43c1-bd9b-50b9af66bddd	t
edf0edc7-e4f0-460a-bc3b-aa761f85fffa	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589971971056	1589971971063	\N	Ola_Hilll51	0	b03e6ee1-2524-4c3c-9985-005a9a4afbb9	t
40a454ac-4bae-46de-a301-e27f96ce250b	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589972084679	1589972088469	\N	Amari.Medhurst	0	748c95bd-8108-4eee-bb1b-f2f74dfa339f	t
4fb0309b-88af-4166-bd44-19c4029f1b3a	3c219e58-ed0e-4b18-ad48-f4f92793ae32	\N	\N	{"data":{},"preferredLanguages":[],"tokens":{}}	1589663506274	1589972402205	\N	\N	0	8c59d7a6-a239-42d0-8886-5abe8545e0dd	t
\.



COPY public.user_registrations_application_roles (application_roles_id, user_registrations_id) FROM stdin;
631ecd9d-8d40-4c13-8277-80cedb8236e2	4fb0309b-88af-4166-bd44-19c4029f1b3a
\.



COPY public.users (id, active, birth_date, clean_speak_id, data, expiry, first_name, full_name, image_url, insert_instant, last_name, middle_name, mobile_phone, parent_email, tenants_id, timezone) FROM stdin;
8c59d7a6-a239-42d0-8886-5abe8545e0dd	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	╨Æ╨░╨┤╨╕╨╝	\N	\N	1589663505580	╨¿╤é╤â╨║╨░╨╜	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
1b656512-68fc-4fa2-b2b9-5a4baef56f06	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	John	\N	\N	1589812264485	\N	\N	303-555-1234	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
389d0689-3f4f-4b49-96f9-c476a2b08c98	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	John	\N	\N	1589819209127	Doe	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
448f52cb-220c-4c49-b548-eb434106879c	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	{{$randomfirstName}}	\N	\N	1589819821606	Feeney	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
3fb27572-c561-4da5-8f83-527d370ea455	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Enid	\N	\N	1589819833854	Barton	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
d0620065-170b-4d66-92a1-59d657534dd0	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Moriah	\N	\N	1589819874914	Okuneva	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
23ebf7a3-f3a9-4f1f-af9e-f883a7f30c2c	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Elta	\N	\N	1589867432064	Connelly	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
5b7ee3bd-53c2-4328-ac35-8113c5cb7486	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Jesse	\N	\N	1589867602290	Nikolaus	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
6d2e5741-f635-4b9b-8df1-0c9e5768ef36	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Rose	\N	\N	1589873716744	Kassulke	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
0f4577e7-0c2e-4f2e-90b9-416386ec5cf8	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	CHANGED John	\N	\N	1589873718348	CHANGED Doe	\N	CHANGED +71002003040	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
ccfa3a5c-4e32-474e-9208-cedfcf1885ef	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Selmer	\N	\N	1589905871162	Howe	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
9c77333a-8770-4728-a171-313747d93285	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Emelie	\N	\N	1589910000255	Hackett	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
ec515da7-5a9e-463e-934b-b156265f459a	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Geo	\N	\N	1589966896306	Marks	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
fa4266c2-f2f3-43d2-93c5-771e3cff42bd	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Brant	\N	\N	1589967462315	Miller	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
c2a88b3b-3a72-43c1-bd9b-50b9af66bddd	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Mack	\N	\N	1589967853964	Beahan	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
b03e6ee1-2524-4c3c-9985-005a9a4afbb9	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Erich	\N	\N	1589971970910	Kohler	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
748c95bd-8108-4eee-bb1b-f2f74dfa339f	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	CHANGED John1	\N	\N	1589972084574	CHANGED Doe1	\N	CHANGED +710020030401	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
529bd8a3-3aab-4907-81e0-c6b155881186	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	CHANGED John12	\N	\N	1589972439510	CHANGED Doe12	\N	CHANGED +710020030401	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
76789ec2-79a6-4c37-ba00-0fda4b369e96	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Rupert	\N	\N	1589974020416	Keebler	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
61e27900-437f-4b4f-90bf-b56640cd1aeb	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Calista	\N	\N	1589974029000	Little	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
b02d1a23-af36-40e4-b5e0-c8a605a14b93	t	\N	\N	{"data":{},"preferredLanguages":[]}	\N	Dallin	\N	\N	1589974030761	Hilll	\N	\N	\N	9241a956-aead-abfc-c78c-d3c1cd5ade42	\N
\.




COPY public.version (version) FROM stdin;
1.16.0
\.



COPY public.webhooks (id, connect_timeout, description, data, global, headers, http_authentication_username, http_authentication_password, read_timeout, ssl_certificate, url) FROM stdin;
\.



COPY public.webhooks_applications (webhooks_id, applications_id) FROM stdin;
\.



SELECT pg_catalog.setval('public.audit_logs_id_seq', 9, true);



SELECT pg_catalog.setval('public.event_logs_id_seq', 1, true);



SELECT pg_catalog.setval('public.identities_id_seq', 147, true);


SELECT pg_catalog.setval('public.user_consents_email_plus_id_seq', 1, false);


ALTER TABLE ONLY public.application_daily_active_users
    ADD CONSTRAINT application_daily_active_users_uk_1 UNIQUE (applications_id, day);



ALTER TABLE ONLY public.application_monthly_active_users
    ADD CONSTRAINT application_monthly_active_users_uk_1 UNIQUE (applications_id, month);



ALTER TABLE ONLY public.application_registration_counts
    ADD CONSTRAINT application_registration_counts_uk_1 UNIQUE (applications_id, hour);



ALTER TABLE ONLY public.application_roles
    ADD CONSTRAINT application_roles_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.application_roles
    ADD CONSTRAINT application_roles_uk_1 UNIQUE (name, applications_id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_uk_1 UNIQUE (name, tenants_id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_uk_2 UNIQUE (samlv2_issuer, tenants_id);


ALTER TABLE ONLY public.audit_logs
    ADD CONSTRAINT audit_logs_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.authentication_keys
    ADD CONSTRAINT authentication_keys_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.breached_password_metrics
    ADD CONSTRAINT breached_password_metrics_pkey PRIMARY KEY (tenants_id);


ALTER TABLE ONLY public.clean_speak_applications
    ADD CONSTRAINT clean_speak_applications_uk_1 UNIQUE (applications_id, clean_speak_application_id);



ALTER TABLE ONLY public.common_breached_passwords
    ADD CONSTRAINT common_breached_passwords_pkey PRIMARY KEY (password);


ALTER TABLE ONLY public.consents
    ADD CONSTRAINT consents_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.consents
    ADD CONSTRAINT consents_uk_1 UNIQUE (name);



ALTER TABLE ONLY public.data_sets
    ADD CONSTRAINT data_sets_pkey PRIMARY KEY (name);


ALTER TABLE ONLY public.email_templates
    ADD CONSTRAINT email_templates_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.email_templates
    ADD CONSTRAINT email_templates_uk_1 UNIQUE (name);




ALTER TABLE ONLY public.event_logs
    ADD CONSTRAINT event_logs_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.external_identifiers
    ADD CONSTRAINT external_identifiers_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.failed_logins
    ADD CONSTRAINT failed_logins_uk_1 UNIQUE (users_id);



ALTER TABLE ONLY public.families
    ADD CONSTRAINT families_pkey PRIMARY KEY (family_id, users_id);



ALTER TABLE ONLY public.federated_domains
    ADD CONSTRAINT federated_domains_uk_1 UNIQUE (domain);



ALTER TABLE ONLY public.global_daily_active_users
    ADD CONSTRAINT global_daily_active_users_uk_1 UNIQUE (day);



ALTER TABLE ONLY public.global_monthly_active_users
    ADD CONSTRAINT global_monthly_active_users_uk_1 UNIQUE (month);



ALTER TABLE ONLY public.global_registration_counts
    ADD CONSTRAINT global_registration_counts_uk_1 UNIQUE (hour);



ALTER TABLE ONLY public.group_application_roles
    ADD CONSTRAINT group_application_roles_uk_1 UNIQUE (groups_id, application_roles_id);



ALTER TABLE ONLY public.group_members
    ADD CONSTRAINT group_members_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.group_members
    ADD CONSTRAINT group_members_uk_1 UNIQUE (groups_id, users_id);



ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_uk_1 UNIQUE (name, tenants_id);



ALTER TABLE ONLY public.hourly_logins
    ADD CONSTRAINT hourly_logins_uk_1 UNIQUE (applications_id, hour);



ALTER TABLE ONLY public.identities
    ADD CONSTRAINT identities_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.identities
    ADD CONSTRAINT identities_uk_1 UNIQUE (email, tenants_id);



ALTER TABLE ONLY public.identities
    ADD CONSTRAINT identities_uk_2 UNIQUE (username_index, tenants_id);



ALTER TABLE ONLY public.identity_providers
    ADD CONSTRAINT identity_providers_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.identity_providers
    ADD CONSTRAINT identity_providers_uk_1 UNIQUE (name);



ALTER TABLE ONLY public.keys
    ADD CONSTRAINT keys_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.keys
    ADD CONSTRAINT keys_uk_1 UNIQUE (kid);



ALTER TABLE ONLY public.keys
    ADD CONSTRAINT keys_uk_2 UNIQUE (name);


ALTER TABLE ONLY public.lambdas
    ADD CONSTRAINT lambdas_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.locks
    ADD CONSTRAINT locks_pkey PRIMARY KEY (type);


ALTER TABLE ONLY public.migrations
    ADD CONSTRAINT migrations_pkey PRIMARY KEY (name);



ALTER TABLE ONLY public.nodes
    ADD CONSTRAINT nodes_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.previous_passwords
    ADD CONSTRAINT previous_passwords_uk_1 UNIQUE (users_id, create_instant);



ALTER TABLE ONLY public.raw_application_daily_active_users
    ADD CONSTRAINT raw_application_daily_active_users_uk_1 UNIQUE (applications_id, day, users_id);



ALTER TABLE ONLY public.raw_application_monthly_active_users
    ADD CONSTRAINT raw_application_monthly_active_users_uk_1 UNIQUE (applications_id, month, users_id);



ALTER TABLE ONLY public.raw_global_daily_active_users
    ADD CONSTRAINT raw_global_daily_active_users_uk_1 UNIQUE (day, users_id);



ALTER TABLE ONLY public.raw_global_monthly_active_users
    ADD CONSTRAINT raw_global_monthly_active_users_uk_1 UNIQUE (month, users_id);



ALTER TABLE ONLY public.refresh_tokens
    ADD CONSTRAINT refresh_tokens_pkey PRIMARY KEY (token);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_uk_1 UNIQUE (name);



ALTER TABLE ONLY public.themes
    ADD CONSTRAINT themes_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.themes
    ADD CONSTRAINT themes_uk_1 UNIQUE (name);


ALTER TABLE ONLY public.user_action_logs
    ADD CONSTRAINT user_action_logs_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_action_reasons
    ADD CONSTRAINT user_action_reasons_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_action_reasons
    ADD CONSTRAINT user_action_reasons_uk_1 UNIQUE (text);


ALTER TABLE ONLY public.user_action_reasons
    ADD CONSTRAINT user_action_reasons_uk_2 UNIQUE (code);


ALTER TABLE ONLY public.user_actions
    ADD CONSTRAINT user_actions_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_actions
    ADD CONSTRAINT user_actions_uk_1 UNIQUE (name);



ALTER TABLE ONLY public.user_comments
    ADD CONSTRAINT user_comments_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_consents_email_plus
    ADD CONSTRAINT user_consents_email_plus_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.user_consents
    ADD CONSTRAINT user_consents_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_registrations_application_roles
    ADD CONSTRAINT user_registrations_application_roles_uk_1 UNIQUE (user_registrations_id, application_roles_id);



ALTER TABLE ONLY public.user_registrations
    ADD CONSTRAINT user_registrations_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.user_registrations
    ADD CONSTRAINT user_registrations_uk_1 UNIQUE (applications_id, users_id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.webhooks_applications
    ADD CONSTRAINT webhooks_applications_pkey PRIMARY KEY (webhooks_id, applications_id);



ALTER TABLE ONLY public.webhooks
    ADD CONSTRAINT webhooks_pkey PRIMARY KEY (id);



CREATE INDEX applications_i_1 ON public.applications USING btree (tenants_id);



CREATE INDEX audit_logs_i1 ON public.audit_logs USING btree (insert_instant);



CREATE INDEX event_logs_i1 ON public.event_logs USING btree (insert_instant);



CREATE INDEX external_identifiers_i_1 ON public.external_identifiers USING btree (tenants_id, type, insert_instant);



CREATE INDEX external_identifiers_i_2 ON public.external_identifiers USING btree (type, users_id, applications_id);



CREATE INDEX failed_logins_i_1 ON public.failed_logins USING btree (tenants_id, last_failed_instant);


CREATE INDEX families_i_1 ON public.families USING btree (users_id);


CREATE INDEX group_members_i_1 ON public.group_members USING btree (users_id);



CREATE INDEX identities_i_1 ON public.identities USING btree (users_id);



CREATE INDEX raw_logins_i_1 ON public.raw_logins USING btree (instant);


CREATE INDEX raw_logins_i_2 ON public.raw_logins USING btree (users_id, instant);



CREATE INDEX refresh_tokens_i_1 ON public.refresh_tokens USING btree (start_instant);



CREATE INDEX refresh_tokens_i_3 ON public.refresh_tokens USING btree (applications_id);


CREATE INDEX user_action_logs_i_1 ON public.user_action_logs USING btree (create_instant);



CREATE INDEX user_action_logs_i_2 ON public.user_action_logs USING btree (expiry, end_event_sent);



CREATE INDEX user_comments_i_1 ON public.user_comments USING btree (create_instant);



CREATE INDEX user_comments_i_2 ON public.user_comments USING btree (users_id);



CREATE INDEX user_comments_i_3 ON public.user_comments USING btree (commenter_id);



CREATE INDEX user_consents_email_plus_i_1 ON public.user_consents_email_plus USING btree (next_email_instant);



CREATE INDEX user_registrations_i_1 ON public.user_registrations USING btree (clean_speak_id);



CREATE INDEX user_registrations_i_2 ON public.user_registrations USING btree (users_id);



CREATE INDEX users_i_1 ON public.users USING btree (clean_speak_id);



CREATE INDEX users_i_2 ON public.users USING btree (parent_email);



ALTER TABLE ONLY public.application_daily_active_users
    ADD CONSTRAINT application_daily_active_users_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.application_monthly_active_users
    ADD CONSTRAINT application_monthly_active_users_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.application_registration_counts
    ADD CONSTRAINT application_registration_counts_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.application_roles
    ADD CONSTRAINT application_roles_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_1 FOREIGN KEY (verification_email_templates_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_2 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_3 FOREIGN KEY (access_token_populate_lambdas_id) REFERENCES public.lambdas(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_4 FOREIGN KEY (id_token_populate_lambdas_id) REFERENCES public.lambdas(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_5 FOREIGN KEY (samlv2_keys_id) REFERENCES public.keys(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_6 FOREIGN KEY (samlv2_populate_lambdas_id) REFERENCES public.lambdas(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_7 FOREIGN KEY (access_token_signing_keys_id) REFERENCES public.keys(id);



ALTER TABLE ONLY public.applications
    ADD CONSTRAINT applications_fk_8 FOREIGN KEY (id_token_signing_keys_id) REFERENCES public.keys(id);


ALTER TABLE ONLY public.authentication_keys
    ADD CONSTRAINT authentication_keys_fk_1 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);



ALTER TABLE ONLY public.breached_password_metrics
    ADD CONSTRAINT breached_password_metrics_fk_1 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);


ALTER TABLE ONLY public.clean_speak_applications
    ADD CONSTRAINT clean_speak_applications_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.consents
    ADD CONSTRAINT consents_fk_1 FOREIGN KEY (consent_email_templates_id) REFERENCES public.email_templates(id);


ALTER TABLE ONLY public.consents
    ADD CONSTRAINT consents_fk_2 FOREIGN KEY (email_plus_email_templates_id) REFERENCES public.email_templates(id);


ALTER TABLE ONLY public.external_identifiers
    ADD CONSTRAINT external_identifiers_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.external_identifiers
    ADD CONSTRAINT external_identifiers_fk_2 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.external_identifiers
    ADD CONSTRAINT external_identifiers_fk_3 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);



ALTER TABLE ONLY public.failed_logins
    ADD CONSTRAINT failed_logins_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.failed_logins
    ADD CONSTRAINT failed_logins_fk_2 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);



ALTER TABLE ONLY public.families
    ADD CONSTRAINT families_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.federated_domains
    ADD CONSTRAINT federated_domains_fk_1 FOREIGN KEY (identity_providers_id) REFERENCES public.identity_providers(id);



ALTER TABLE ONLY public.group_application_roles
    ADD CONSTRAINT group_application_roles_fk_1 FOREIGN KEY (groups_id) REFERENCES public.groups(id);



ALTER TABLE ONLY public.group_application_roles
    ADD CONSTRAINT group_application_roles_fk_2 FOREIGN KEY (application_roles_id) REFERENCES public.application_roles(id);



ALTER TABLE ONLY public.group_members
    ADD CONSTRAINT group_members_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.group_members
    ADD CONSTRAINT group_members_fk_2 FOREIGN KEY (groups_id) REFERENCES public.groups(id);



ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_fk_1 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);



ALTER TABLE ONLY public.hourly_logins
    ADD CONSTRAINT hourly_logins_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.identities
    ADD CONSTRAINT identities_fk_1 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);



ALTER TABLE ONLY public.identities
    ADD CONSTRAINT identities_fk_2 FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.identity_providers_applications
    ADD CONSTRAINT identity_providers_applications_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.identity_providers_applications
    ADD CONSTRAINT identity_providers_applications_fk_2 FOREIGN KEY (identity_providers_id) REFERENCES public.identity_providers(id);



ALTER TABLE ONLY public.identity_providers
    ADD CONSTRAINT identity_providers_fk_1 FOREIGN KEY (keys_id) REFERENCES public.keys(id);



ALTER TABLE ONLY public.identity_providers
    ADD CONSTRAINT identity_providers_fk_2 FOREIGN KEY (reconcile_lambdas_id) REFERENCES public.lambdas(id);


ALTER TABLE ONLY public.previous_passwords
    ADD CONSTRAINT previous_passwords_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.raw_logins
    ADD CONSTRAINT raw_logins_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.raw_logins
    ADD CONSTRAINT raw_logins_fk_2 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.refresh_tokens
    ADD CONSTRAINT refresh_tokens_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.refresh_tokens
    ADD CONSTRAINT refresh_tokens_fk_2 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_1 FOREIGN KEY (forgot_password_email_templates_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_10 FOREIGN KEY (access_token_signing_keys_id) REFERENCES public.keys(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_11 FOREIGN KEY (id_token_signing_keys_id) REFERENCES public.keys(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_2 FOREIGN KEY (set_password_email_templates_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_3 FOREIGN KEY (verification_email_templates_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_4 FOREIGN KEY (passwordless_email_templates_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_5 FOREIGN KEY (confirm_child_email_template_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_6 FOREIGN KEY (family_request_email_template_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_7 FOREIGN KEY (parent_registration_email_template_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_8 FOREIGN KEY (failed_authentication_user_actions_id) REFERENCES public.user_actions(id);



ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_fk_9 FOREIGN KEY (themes_id) REFERENCES public.themes(id);



ALTER TABLE ONLY public.user_action_logs_applications
    ADD CONSTRAINT user_action_logs_applications_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id) ON DELETE CASCADE;



ALTER TABLE ONLY public.user_action_logs_applications
    ADD CONSTRAINT user_action_logs_applications_fk_2 FOREIGN KEY (user_action_logs_id) REFERENCES public.user_action_logs(id) ON DELETE CASCADE;



ALTER TABLE ONLY public.user_action_logs
    ADD CONSTRAINT user_action_logs_fk_1 FOREIGN KEY (actioner_users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.user_action_logs
    ADD CONSTRAINT user_action_logs_fk_2 FOREIGN KEY (actionee_users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.user_action_logs
    ADD CONSTRAINT user_action_logs_fk_3 FOREIGN KEY (user_actions_id) REFERENCES public.user_actions(id);



ALTER TABLE ONLY public.user_actions
    ADD CONSTRAINT user_actions_fk_1 FOREIGN KEY (cancel_email_templates_id) REFERENCES public.email_templates(id);


ALTER TABLE ONLY public.user_actions
    ADD CONSTRAINT user_actions_fk_2 FOREIGN KEY (end_email_templates_id) REFERENCES public.email_templates(id);


ALTER TABLE ONLY public.user_actions
    ADD CONSTRAINT user_actions_fk_3 FOREIGN KEY (modify_email_templates_id) REFERENCES public.email_templates(id);



ALTER TABLE ONLY public.user_actions
    ADD CONSTRAINT user_actions_fk_4 FOREIGN KEY (start_email_templates_id) REFERENCES public.email_templates(id);


ALTER TABLE ONLY public.user_comments
    ADD CONSTRAINT user_comments_fk_1 FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.user_comments
    ADD CONSTRAINT user_comments_fk_2 FOREIGN KEY (commenter_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.user_consents_email_plus
    ADD CONSTRAINT user_consents_email_plus_fk_1 FOREIGN KEY (user_consents_id) REFERENCES public.user_consents(id);



ALTER TABLE ONLY public.user_consents
    ADD CONSTRAINT user_consents_fk_1 FOREIGN KEY (consents_id) REFERENCES public.consents(id);



ALTER TABLE ONLY public.user_consents
    ADD CONSTRAINT user_consents_fk_2 FOREIGN KEY (giver_users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.user_consents
    ADD CONSTRAINT user_consents_fk_3 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.user_registrations_application_roles
    ADD CONSTRAINT user_registrations_application_roles_fk_1 FOREIGN KEY (user_registrations_id) REFERENCES public.user_registrations(id);


ALTER TABLE ONLY public.user_registrations_application_roles
    ADD CONSTRAINT user_registrations_application_roles_fk_2 FOREIGN KEY (application_roles_id) REFERENCES public.application_roles(id);



ALTER TABLE ONLY public.user_registrations
    ADD CONSTRAINT user_registrations_fk_1 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



ALTER TABLE ONLY public.user_registrations
    ADD CONSTRAINT user_registrations_fk_2 FOREIGN KEY (users_id) REFERENCES public.users(id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_fk_1 FOREIGN KEY (tenants_id) REFERENCES public.tenants(id);


ALTER TABLE ONLY public.webhooks_applications
    ADD CONSTRAINT webhooks_applications_fk_1 FOREIGN KEY (webhooks_id) REFERENCES public.webhooks(id);


ALTER TABLE ONLY public.webhooks_applications
    ADD CONSTRAINT webhooks_applications_fk_2 FOREIGN KEY (applications_id) REFERENCES public.applications(id);



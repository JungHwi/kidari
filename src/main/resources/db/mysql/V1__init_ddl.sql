create table lecture
(
    id            bigint auto_increment primary key comment '강연 ID',
    lecturer      varchar(10)  not null comment '강연자',
    hall          varchar(30)  not null comment '강연장',
    max_applicant int          not null comment '최대 신청인원',
    description   varchar(500) not null comment '설명',
    start_at      datetime     not null comment '시작 시간',
    created_at    datetime     not null comment '생성 시간'
) comment '강연 정보';

create table enrollment
(
    id         bigint auto_increment primary key comment '강연 등록 ID',
    lecture_id bigint     not null comment '강연 ID',
    member_id  varchar(5) not null comment '사번',
    created_at datetime   not null comment '강연 등록 시간',
    unique key unique_enrollment_lecture_member (lecture_id, member_id),
    index idx_enrollment_member (member_id)
) comment '강연 등록 정보';
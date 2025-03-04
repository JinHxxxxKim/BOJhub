# -- 코드를 입력하세요
# # 2022년 4월 13일 취소되지 않은 흉부외과(CS) 
# WITH APPO AS (
#     SELECT 
#         APNT_YMD
#         , APNT_NO
#         , PT_NO
#         , MCDP_CD
#         , MDDR_ID
#     FROM APPOINTMENT 
#     WHERE 
#         MCDP_CD = 'CS' 
#         AND 
#         APNT_CNCL_YN = 'N' 
#         AND 
#         DATE_FORMAT(APNT_YMD, '%Y-%m-%d') = '2022-04-13'
# )

# SELECT 
#     A.APNT_NO
#     , P.PT_NAME
#     , P.PT_NO
#     , A.MCDP_CD
#     , D.DR_NAME
#     , DATE_FORMAT(A.APNT_YMD, '%Y-%m-%d %H:%i:%s.%f') AS APNT_YMD
# FROM 
#     PATIENT P
#     JOIN
#     APPO A
#     ON P.PT_NO = A.PT_NO
#     JOIN DOCTOR D
#     ON D.DR_ID = A.MDDR_ID
# ORDER BY APNT_YMD ASC;

SELECT 
    A.APNT_NO,       -- 진료 예약 번호
    P.PT_NAME,       -- 환자 이름
    P.PT_NO,         -- 환자 번호
    A.MCDP_CD,       -- 진료과 코드
    D.DR_NAME,       -- 의사 이름
    A.APNT_YMD       -- 진료 예약 일시
FROM 
    APPOINTMENT A
    JOIN PATIENT P ON A.PT_NO = P.PT_NO
    JOIN DOCTOR D ON A.MDDR_ID = D.DR_ID
WHERE 
    A.MCDP_CD = 'CS'                  -- 흉부외과(CS) 예약만 조회
    AND A.APNT_CNCL_YN = 'N'          -- 취소되지 않은 예약
    AND DATE(A.APNT_YMD) = '2022-04-13'  -- 2022년 4월 13일 예약
ORDER BY 
    A.APNT_YMD ASC;  -- 진료 예약 일시 기준 오름차순 정렬

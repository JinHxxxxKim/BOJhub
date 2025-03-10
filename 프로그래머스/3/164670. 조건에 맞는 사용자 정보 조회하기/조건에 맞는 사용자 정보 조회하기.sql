-- 코드를 입력하세요
#  중고 거래 게시물을 3건 이상 등록한 사용자의 사용자


SELECT 
    USER_ID
    , NICKNAME
    , CONCAT(CITY, ' ', STREET_ADDRESS1, ' ', STREET_ADDRESS2) AS '전체주소'
    , CONCAT(LEFT(TLNO, 3), '-', SUBSTRING(TLNO, 4, 4), '-', RIGHT(TLNO, 4)) AS '전화번호'
FROM 
    USED_GOODS_USER 
WHERE
    USER_ID IN (SELECT WRITER_ID FROM USED_GOODS_BOARD GROUP BY WRITER_ID HAVING COUNT(BOARD_ID) >= 3)
ORDER BY USER_ID DESC;

# SELECT WRITER_ID, COUNT(*) FROM USED_GOODS_BOARD GROUP BY WRITER_ID;
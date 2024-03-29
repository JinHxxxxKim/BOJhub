# 같은 달, 같은 USER_ID에 대해서는 1번만 카운팅해야한다.
SELECT 
    YEAR(S.SALES_DATE) AS YEAR, 
    MONTH(S.SALES_DATE) AS MONTH, 
    U.GENDER AS GENDER, 
    COUNT(DISTINCT U.USER_ID) AS USERS
FROM USER_INFO U JOIN ONLINE_SALE S
ON U.USER_ID = S.USER_ID
WHERE U.GENDER IS NOT NULL 
GROUP BY
    YEAR(S.SALES_DATE), MONTH(S.SALES_DATE), U.GENDER
ORDER BY 
    YEAR, MONTH, GENDER
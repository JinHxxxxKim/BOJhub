# -- 2022년도 한해 평가 점수가 가장 높은 사원 정보를 조회
WITH MAX_SCORE AS (
    SELECT 
        SUM(SCORE) AS SUM_SCORE 
    FROM 
        HR_GRADE
    WHERE YEAR = '2022'
    GROUP BY 
        EMP_NO 
    ORDER BY SUM_SCORE DESC
    LIMIT 1
), MAX_SCORES AS (
    SELECT 
        EMP_NO, SUM(SCORE) AS SUM_SCORE 
    FROM 
        HR_GRADE
    WHERE YEAR = '2022'
    GROUP BY 
        EMP_NO 
    HAVING 
        SUM(SCORE) = (SELECT SUM_SCORE FROM MAX_SCORE)
    ORDER BY SUM_SCORE DESC
)

SELECT 
    S.SUM_SCORE AS SCORE
    , E.EMP_NO
    , E.EMP_NAME
    , E.POSITION
    , E.EMAIL
FROM  
    HR_EMPLOYEES E JOIN MAX_SCORES S ON E.EMP_NO = S.EMP_NO
    JOIN HR_DEPARTMENT D ON E.DEPT_ID = D.DEPT_ID;
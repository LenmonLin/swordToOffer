/**
编写一个 SQL 查询来实现分数排名。
如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是
下一个连续的整数值。换句话说，名次之间不应该有“间隔”。
+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：
+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
思路：https://leetcode-cn.com/problems/rank-scores/solution/fen-cheng-liang-ge-bu-fen-xie-hui-rong-yi-hen-duo-/
第一部分是降序排列的分数，第二部分是每个分数对应的排名。
比较难的是第二部分。假设现在给你一个分数3.85，如何算出它的排名Rank呢？
先把分数用distinct去重，然后用count统计比3.85大成绩有1个，如果是大于等于就有2个，
所以3.85排名为2
 */
 select a.Score as Score,
        (select count(distinct b.Score) from Scores b where b.Score >= a.Score)
        as "Rank" #因为rank是关键字，所以需要加双引号
 from Scores as a
 order by a.Score desc


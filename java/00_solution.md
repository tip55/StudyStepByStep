# 常见排序算法
* 冒泡排序
![Alt text](./images/BubbleSort.gif "冒泡排序")
```
排序思路：
依次比较相邻的两个元素，并通过交换位置使其满足指定顺序。
特别注意点：
遍历的次数应等于 length-1 。
```
```java
public class Solution{
    public int[] bubbleSort(int[] arrays){
        for(int i=0; i <= arrays.length -1 -1; i++){
            for(int j=0; j <= arrays.length -1 -1 - i; j++){
                if(arrays[j] < arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
        return arrays;
    }
}
```


# 常见查找算法


# 红黑树
* 特征
```
```
* 左旋与右旋
```
```

# 动态规划
```
初始边界状态 | 状态转移方程
```
* 背包问题

* 股票最大收益
```
问题描述：
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润，你最多可以完成两笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例：
输入 / prices = [3,3,5,0,0,3,1,4]
输出 / 6
解释 / 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

思路与算法：
由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种 /
a. 未进行过任何操作；
b. 只进行过一次买操作；
c. 进行了一次买操作和一次卖操作，即完成了一笔交易；
d. 在完成了一笔交易的前提下，进行了第二次买操作；
e. 完成了全部两笔交易。
由于第一个状态的利润显然为 0，因此我们可以不用将其记录。对于剩下的四个状态，我们分别将它们的最大利润记为 buy1 / sell1 / buy2 / sell2.

如果我们知道了第 i-1 天结束后的这四个状态，那么如何通过状态转移方程得到第 i 天结束后的这四个状态呢？
对于 buy1 而言，在第 i 天我们可以不进行任何操作，保持不变，也可以在未进行任何操作的前提下以 prices[i] 的价格买入股票，那么 buy1 的状态转移方程即为：
buy1 = max{buy1′, −prices[i]}
这里我们用 buy1′ 表示第 i-1 天的状态。

对于 sell1 而言，在第 i 天我们可以不进行任何操作，保持不变，也可以在只进行过一次买操作的前提下以 prices[i] 的价格卖出股票，那么 sell1 的状态转移方程即为：
sell1 = max{sell1′, buy1′+prices[i]}

同理我们可以得到 buy2 和 sell2 对应的状态转移方程：
buy2 = max{buy2′, sell1′−prices[i]}
sell2 = max{sell2′, buy2′+prices[i]}

在考虑边界条件时，我们需要理解下面的这个事实：
无论题目中是否允许「在同一天买入并且卖出」这一操作，最终的答案都不会受到影响，这是因为这一操作带来的收益为零。

因此状态转移方程可以变为：
buy1 = max{buy1, −prices[i]}
sell1 = max{sell1, buy1+prices[i]}
buy2 = max{buy2, sell1−prices[i]}
sell2 = max{sell2, buy2+prices[i]}


JAVA代码：
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}

时间复杂度：O(n)，其中 n 是数组 prices 的长度。
空间复杂度：O(1)。
```



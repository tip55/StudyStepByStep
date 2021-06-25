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
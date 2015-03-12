/*
连连看游戏中允许消除的条件有两个：
1. 玩家点击的两个图案相同
2. 两个图形之间存在不超过两个弯的路径。

主要是第二个条件的判断。
思路：等价于在板子上寻找两个相同图片节点之间的最短路径，同时该路径不超过两个弯。
1. 仍沿用图中求最短路径的广度遍历BFS的思路，求两点之间最少弯数的路径
2. 利用本题的特点：直线路径长度 ≤ 带有一个弯的路径长度 ≤ 带有两个弯的路径长度
把问题分解：
1. 先求A点出发的直线路径上是否有B，是则返回true；
2. 否则求A出发带一个弯的路径上是否有B，是则返回true；
3. 否则求带两个弯的路径上是否有B。
A点出发的一个弯的路径，加上B点，一起构成了一个矩形AYBX
A - - - -Y
|            |
X - - - -B
A点出发的两个个弯的路径，相当于每次尝试把AY向上拉一格，AX向右拉一格。一直到板子的边界。
      - - - - - Y
      |           |
- - A           |
|                 |
X - - - - - - B

可以看出，第二种方式效率较高。
实现的时候，基础操作是判断A点出发的水平和垂直直线路径是否能连通B，因此我们用一个辅助的二维数组，来保存板子上每个格子是否有图片。
*/

(function(){
    var lianliankanTool = function(config){
        var config = config || {};
        this.width = config.width || 10;
        this.height = config.height || 10;
        var defaultMap = new Array(this.height);
        for (var i = 0; i < this.height; i++) {
            defaultMap[i] = new Array(this.width)
        }
        this.map = config.map || defaultMap;
    }

    // Check if the x-axis that start sits is clear
    function checkX(xStart, yStart, xEnd){
        for(var i = xStart; i != xEnd; (i < xEnd ? i++ : i--){
            if(!!this.map[i][yStart]){  // some one get in the way
                return false;
            }
        }
        return true;
    }

    // Check if the y-axis that start sits is clear
    function checkY(xStart, yStart, yEnd){
        for(var i = yStart; i != yEnd; (i < yEnd ? i++ : i--){
            if(!!this.map[xStart][i]){  // some one get in the way
                return false;
            }
        }
        return true;
    }

    lianliankanTool.prototype.checkLinked = function(p1, p2){
        // ajust p1 and p2 to make sure p1 is left-above of p2
        var atLeft = false, atAbove= false;
        if (p1.x < p2.x) {
            atLeft = true;
        }
        if (p1.y > p2.y) {
            atAbove = true;
        }

        // chech connectivity on straight line
        if (checkY(p1.y+1, p1.x, p2.y) && checkX(p1.x + 1, px.y, p2.x) {
            return true;
        }

        // check connectivity with one turn
        if (atLeft && atAbove) {
            if (checkX(p1.x+1, p1.y, p2.x) && checkY(p2.x, p1.y+1, p2.y+1) ||
                checkY(p1.x, p1.y+1, p2.y) && checkX(p1.x+1, p2.y, p2.x-1)) {
                return true;
            }
        }else if (!atLeft && atAbove) {

        }else if (atLeft && !atAbove) {

        }else if (!atLeft && !atAbove) {

        }

        // check connectivity with two turns
    }

    window.lianliankanTool = lianliankanTool;
})();
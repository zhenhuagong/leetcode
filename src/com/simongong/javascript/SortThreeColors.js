(function(){
    var ThreeColorsSorter = function(n){
        this.n = n || 100;
    }

    ThreeColorsSorter.prototype.sort = function (n){
        var n = n || this.n;
        if (n <= 0) {
            return;
        }

        // generate shuffled array
        var data = _getShuffledData(n);
        console.log(data);

        // count the occurrence of each color
        var counts = [0, 0, 0];
        for(var i = 0; i < n; i++){
            counts[data[i]]++;
        }

        // group the elements of data by color
        for(i = 0; i < n; i++){
            if (counts[0] > 0){
                data[i] = 0;
                counts[0]--;
            }else if(counts[1] > 0){
                data[i] = 1;
                counts[1]--;
            }else{
                data[i] = 2;
            }
        }

        console.log(data);
    }

    // generate shuffled data
    function _getShuffledData(n){
        var data = [];
        for (var j = 0; j < n; j++) {
            data.push(parseInt(Math.random() * 3));
        }
        return data;
    }

    window.ThreeColorsSorter = ThreeColorsSorter;
})();
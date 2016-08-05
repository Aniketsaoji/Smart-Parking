var app = angular.module('TrafficApp', ['ng-fusioncharts']);

app.controller('chartController', function($scope) {
    //chart definition
    $scope.dataSource =  {
        "chart": {
            "caption": "Box Office Earnings - 2015",
            "xAxisName": "Month",
            //more chart properties
        },
        "data": [
            { "label": "Jan", "value": "420000" },
            { "label": "Feb", "value": "810000" },
            //more chart data
        ]
    };
});

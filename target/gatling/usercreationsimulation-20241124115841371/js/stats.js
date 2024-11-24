var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "22500",
        "ok": "20043",
        "ko": "2457"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "127",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "23258",
        "ok": "23258",
        "ko": "1311"
    },
    "meanResponseTime": {
        "total": "7912",
        "ok": "8881",
        "ko": "8"
    },
    "standardDeviation": {
        "total": "5176",
        "ok": "4634",
        "ko": "56"
    },
    "percentiles1": {
        "total": "8069",
        "ok": "9524",
        "ko": "1"
    },
    "percentiles2": {
        "total": "12928",
        "ok": "13283",
        "ko": "2"
    },
    "percentiles3": {
        "total": "14989",
        "ok": "15184",
        "ko": "29"
    },
    "percentiles4": {
        "total": "17209",
        "ok": "17372",
        "ko": "124"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 173,
    "percentage": 1
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 426,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 19444,
    "percentage": 86
},
    "group4": {
    "name": "failed",
    "count": 2457,
    "percentage": 11
},
    "meanNumberOfRequestsPerSecond": {
        "total": "220.588",
        "ok": "196.5",
        "ko": "24.088"
    }
},
contents: {
"req_register-user-r-19d8e": {
        type: "REQUEST",
        name: "register-user-request",
path: "register-user-request",
pathFormatted: "req_register-user-r-19d8e",
stats: {
    "name": "register-user-request",
    "numberOfRequests": {
        "total": "22500",
        "ok": "20043",
        "ko": "2457"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "127",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "23258",
        "ok": "23258",
        "ko": "1311"
    },
    "meanResponseTime": {
        "total": "7912",
        "ok": "8881",
        "ko": "8"
    },
    "standardDeviation": {
        "total": "5176",
        "ok": "4634",
        "ko": "56"
    },
    "percentiles1": {
        "total": "8069",
        "ok": "9524",
        "ko": "1"
    },
    "percentiles2": {
        "total": "12928",
        "ok": "13284",
        "ko": "2"
    },
    "percentiles3": {
        "total": "14987",
        "ok": "15185",
        "ko": "29"
    },
    "percentiles4": {
        "total": "17208",
        "ok": "17374",
        "ko": "124"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 173,
    "percentage": 1
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 426,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 19444,
    "percentage": 86
},
    "group4": {
    "name": "failed",
    "count": 2457,
    "percentage": 11
},
    "meanNumberOfRequestsPerSecond": {
        "total": "220.588",
        "ok": "196.5",
        "ko": "24.088"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}

$("#fcfs").click(function () {
    let i = 1;
    let list = [];
    while (i <= 5) {
        let process = {};
        process.name = $("#name" + i).text();
        process.arrivalTime = $("#arrivalTime" + i).val();
        process.serviceTime = $("#serviceTime" + i).val();
        list[i - 1] = process;
        i++;
    }
    $.ajax({
        url: url + "/jobScheduling/FCFS",
        type: "POST",
        dataType: "json",
        data: {
            processList: JSON.stringify(list),
        },
        traditional: true,
        success: function (e) {
            let results = e.message;
            i = 1;
            while (i <= 5) {
                $("#startTime" + i).html(results[i - 1].startTime);
                $("#finishTime" + i).html(results[i - 1].finishTime);
                $("#turnAroundTime" + i).html(results[i - 1].turnAroundTime);
                $("#weightTurnAroundTime" + i).html(results[i - 1].weightTurnAroundTime);
                i++;
            }
        },
        error: function (e) {
            alert("error");
            console.log(e);
        }
    })
});


$("#sjf").click(function () {
    let i = 1;
    let list = [];
    while (i <= 5) {
        let process = {};
        process.name = $("#name" + i).text();
        process.arrivalTime = $("#arrivalTime" + i).val();
        process.serviceTime = $("#serviceTime" + i).val();
        list[i - 1] = process;
        i++;
    }
    $.ajax({
        url: url + "/jobScheduling/SJF",
        type: "POST",
        dataType: "json",
        data: {
            processList: JSON.stringify(list),
        },
        traditional: true,
        success: function (e) {
            let results = e.message;
            i = 1;
            while (i <= 5) {
                $("#startTime" + i).html(results[i - 1].startTime);
                $("#finishTime" + i).html(results[i - 1].finishTime);
                $("#turnAroundTime" + i).html(results[i - 1].turnAroundTime);
                $("#weightTurnAroundTime" + i).html(results[i - 1].weightTurnAroundTime);
                i++;
            }
        },
        error: function (e) {
            alert("error");
            console.log(e);
        }
    })
});


$("#psa").click(function () {
    let i = 1;
    let list = [];
    while (i <= 5) {
        let process = {};
        process.name = $("#name" + i).text();
        process.arrivalTime = $("#arrivalTime" + i).val();
        process.serviceTime = $("#serviceTime" + i).val();
        process.priority = $("#priority" + i).val();
        list[i - 1] = process;
        i++;
    }
    $.ajax({
        url: url + "/jobScheduling/PSA",
        type: "POST",
        dataType: "json",
        data: {
            processList: JSON.stringify(list),
        },
        traditional: true,
        success: function (e) {
            let results = e.message;
            i = 1;
            while (i <= 5) {
                $("#startTime" + i).html(results[i - 1].startTime);
                $("#finishTime" + i).html(results[i - 1].finishTime);
                $("#turnAroundTime" + i).html(results[i - 1].turnAroundTime);
                $("#weightTurnAroundTime" + i).html(results[i - 1].weightTurnAroundTime);
                i++;
            }
        },
        error: function (e) {
            alert("error");
            console.log(e);
        }
    })
});

$("#hrrn").click(function () {
    let i = 1;
    let list = [];
    while (i <= 5) {
        let process = {};
        process.name = $("#name" + i).text();
        process.arrivalTime = $("#arrivalTime" + i).val();
        process.serviceTime = $("#serviceTime" + i).val();
        list[i - 1] = process;
        i++;
    }
    $.ajax({
        url: url + "/jobScheduling/HRRN",
        type: "POST",
        dataType: "json",
        data: {
            processList: JSON.stringify(list),
        },
        traditional: true,
        success: function (e) {
            let results = e.message;
            i = 1;
            while (i <= 5) {
                $("#startTime" + i).html(results[i - 1].startTime);
                $("#finishTime" + i).html(results[i - 1].finishTime);
                $("#turnAroundTime" + i).html(results[i - 1].turnAroundTime);
                $("#weightTurnAroundTime" + i).html(results[i - 1].weightTurnAroundTime);
                i++;
            }
        },
        error: function (e) {
            alert("error");
            console.log(e);
        }
    })
});
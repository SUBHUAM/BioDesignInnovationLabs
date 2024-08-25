import { chartsConfig } from "@/configs";

const patientVisitsChart = {
  type: "bar",
  height: 220,
  series: [
    {
      name: "Patient Visits",
      data: [120, 180, 150, 200, 250, 220, 170], 
    },
  ],
  options: {
    ...chartsConfig,
    colors: "#388e3c",
    plotOptions: {
      bar: {
        columnWidth: "16%",
        borderRadius: 5,
      },
    },
    xaxis: {
      ...chartsConfig.xaxis,
      categories: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"], 
    },
  },
};

const medicationPrescriptionsChart = {
  type: "line",
  height: 220,
  series: [
    {
      name: "Prescriptions",
      data: [50, 75, 100, 125, 175, 150, 200, 175, 225], 
    },
  ],
  options: {
    ...chartsConfig,
    colors: ["#0288d1"],
    stroke: {
      lineCap: "round",
    },
    markers: {
      size: 5,
    },
    xaxis: {
      ...chartsConfig.xaxis,
      categories: [
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
      ], 
    },
  },
};

const completedTreatmentsChart = {
  type: "line",
  height: 220,
  series: [
    {
      name: "Treatments",
      data: [10, 25, 30, 45, 50, 70, 90, 85, 100], 
    },
  ],
  options: {
    ...chartsConfig,
    colors: ["#388e3c"],
    stroke: {
      lineCap: "round",
    },
    markers: {
      size: 5,
    },
    xaxis: {
      ...chartsConfig.xaxis,
      categories: [
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
      ], 
    },
  },
};


const medicalProcedureChart = {
  type: "pie",
  height: 320,
  series: [35, 25, 15, 10, 15], 
  options: {
    ...chartsConfig,
    labels: ["Surgeries", "Consultations", "Lab Tests", "X-rays", "Therapies"], 
    colors: ["#00c853", "#0288d1", "#ffeb3b", "#d32f2f", "#ff5722"],
    legend: {
      position: 'bottom',
      labels: {
        colors: ['#888888'],
        useSeriesColors: true,
      },
    },
    dataLabels: {
      enabled: true,
      formatter: (val) => `${val.toFixed(1)}%`,
    },
    responsive: [
      {
        breakpoint: 480,
        options: {
          chart: {
            width: 320,
          },
          legend: {
            position: "bottom",
          },
        },
      },
    ],
  },
};


export const statisticsChartsData = [
  {
    color: "white",
    title: "Patient Visits",
    description: "Weekly Patient Visits",
    footer: "updated 2 days ago",
    chart: patientVisitsChart,
  },
  {
    color: "white",
    title: "Medical Procedure Distribution",
    description: "Types of Procedures Performed",
    footer: "data updated this week",
    chart: medicalProcedureChart,
  },
  {
    color: "white",
    title: "Medication Prescriptions",
    description: "Prescription Trends Over Time",
    footer: "updated 10 min ago",
    chart: medicationPrescriptionsChart,
  },
];

export default statisticsChartsData;

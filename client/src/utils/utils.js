export default {
    regexPatterns : {
        email : /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
    },
    rentalPeriodTimes : {
            oneWeek: {
                label: "One week",
                value: "ONE_WEEK"
            },
            twoWeeks: {
                label: "Two weeks",
                value: "TWO_WEEKS"
            },
            threeWeeks: {
                label: "Three weeks",
                value: "THREE_WEEKS"
            },
            oneMonth: {
                label: "One month",
                value: "ONE_MONTH"
            },
            twoMonths: {
                label: "Two months",
                value: "TWO_MONTHS"

            },
            threeMonths: {
                label: "Three months",
                value: "THREE_MONTHS"
            }
    },
    defaultRentalPeriod : {
            oneWeek: {
                label: "One week",
                value: "ONE_WEEK"
            },
            twoWeeks: {
                label: "Two weeks",
                value: "TWO_WEEKS"
            }
    }
}
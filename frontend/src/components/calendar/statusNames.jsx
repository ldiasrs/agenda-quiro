export const statusNames = {
    SCHEDULED: "Agendado",
    FREE: "Livre"
}

export const isFreeStatus = (status) => {
    return statusNames.FREE === statusNames[status]
}

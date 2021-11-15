import api from "./api";

export const handleApiGetItem = async (urlOperation, id) => {
    try {
        const isEditMode = id;
        if (isEditMode) {
            const response = await api.get(`${urlOperation}/${id}`)
            return response.data;
        }
    } catch (error) {
        alert("Ocorreu um erro ao buscar o item: " + error);
    }
}

export const handleApiSubmit = async (e, history, id, urlPut, urlPost, requestData, listRoutePath) => {
    e.preventDefault()
    try {
        const isEditMode = id;
        if (isEditMode) {
            await api.put(`${urlPut}/${id}`, requestData)
        } else {
            await api.post(urlPost, requestData)
        }
        history.push(listRoutePath)
    } catch (error) {
        alert("Ocorreu um erro: " + error);
    }
}

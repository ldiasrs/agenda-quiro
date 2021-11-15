import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import api from "../../services/api";
import {ConfirmationModal} from "../ConfirmationModal";
import {ModalFooter} from "../ModalFooter";

export const AddEditServiceType = ({ history, match }) => {

    const urlOperation = `/service/`;
    const listRoutePath = '/listarservico';
    const { id } = match.params;

    const defaultValues = {
        id : undefined,
        description: "",
        durationInMinutes : 50,
    }

    const [description, setDescription] = useState(defaultValues.description);
    const [durationInMinutes, setDurationInMin] = useState(defaultValues.durationInMinutes);

    useEffect(() => {
        console.log("useEffect")
        async function getItem() {
            try {
                console.log("id: " + id)
                const isEditMode = id;
                if (isEditMode) {
                    const response = await api.get(`/service/${id}`)
                    const serviceType = response.data;
                    setDescription(serviceType.description)
                    setDurationInMin(serviceType.durationInMinutes)
                }
            } catch (error) {
                alert("Ocorreu um erro ao buscar o item: "+error);
            }
        }
        getItem();
    }, [match.params]);

    const handleSubmit = async (e) => {
        e.preventDefault()
        const serviceTypeData = {
            description,
            durationInMinutes,
        }
        try {
            const {id} = match.params;
            const isEditMode = id;
            if (isEditMode) {
                await api.put(`/service/${id}`, serviceTypeData)
            } else {
                await api.post(`/services`, serviceTypeData)
            }
            history.push(listRoutePath)
        } catch (error) {
            alert("Ocorreu um erro: " + error);
        }
        console.log(serviceTypeData)
    }

    return (
        <>
            <NavigationApp/>
            <div  className='customForm'>
                <div id="addServiceTypeModal" className='registerPanel'>
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Tipo de serviço</h2>
                            </div>
                        </div>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <div className="modal-body">
                            <div className="form-group">
                                <label>Descrição</label>
                                <input type="text"
                                       value={description}
                                       onChange={e => setDescription(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Duração em minutos</label>
                                <input type="number"
                                       value={durationInMinutes}
                                       onChange={e => setDurationInMin(e.target.value)}
                                       required className="form-control"/>
                            </div>
                        </div>
                        <ModalFooter listRoutePath={listRoutePath}/>
                    </form>
                </div>
            </div>
           <ConfirmationModal
               history={history}
               deleteApiPath={urlOperation}
               listRoutePath={listRoutePath}
               id={id}
           />
        </>
    )
}

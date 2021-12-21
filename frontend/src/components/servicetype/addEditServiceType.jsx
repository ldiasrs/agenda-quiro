import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import {ConfirmationModal} from "../ConfirmationModal";
import {ModalFooter} from "../ModalFooter";
import {handleApiGetItem, handleApiSubmit} from "../../services/handlesService";

export const AddEditServiceType = ({ history, match }) => {

    const urlOperation = `/service`;
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
            const response = await handleApiGetItem(urlOperation, id)
            if (response) {
                setDescription(response.description)
                setDurationInMin(response.durationInMinutes)
            }
        }
        getItem();
    }, [match.params]);

    const handleSubmit = async (e) => {
        const serviceTypeData = {
            description,
            durationInMinutes,
        }
        await handleApiSubmit(e, history, id, urlOperation, `/services`, serviceTypeData, listRoutePath)
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
                        <ModalFooter listRoutePath={listRoutePath} id={id}/>
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

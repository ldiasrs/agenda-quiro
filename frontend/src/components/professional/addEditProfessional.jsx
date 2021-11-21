import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import "react-datepicker/dist/react-datepicker.css";
import {ConfirmationModal} from "../ConfirmationModal";
import {ModalFooter} from "../ModalFooter";
import {handleApiGetItem, handleApiSubmit} from "../../services/handlesService";

export const AddEditProfessional = ({ history, match }) => {

    const urlOperation = `/professional`;
    const listRoutePath = '/listarprofessional';

    const defaultvalues = {
        id : undefined,
        name: ""
    }

    const [name, setName] = useState(defaultvalues.name);
    const {id} = match.params;

    // com Async Await
    useEffect(() => {
        async function getItem() {
            const response = await handleApiGetItem(urlOperation, id)
            if (response) {
                setName(response.name)
            }
        }
        getItem();
    }, [match.params]);

    const handleSubmit = async (e) => {
        const customerData = {
            name
        }
        await handleApiSubmit(e, history, id, urlOperation, `/professionals`, customerData, listRoutePath)
    }

    return (
        <>
            <NavigationApp/>
            <div  className='customForm'>
                <div id="addProfessionalModal" className='registerPanel'>
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Professional</h2>
                            </div>
                        </div>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <div className="modal-body">
                            <div className="form-group">
                                <label>Name</label>
                                <input type="text"
                                       value={name}
                                       onChange={e => setName(e.target.value)}
                                       className="form-control" required/>
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

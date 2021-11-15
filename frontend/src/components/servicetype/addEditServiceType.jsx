import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import api from "../../services/api";

export const AddEditServiceType = ({ history, match }) => {

    const urlOperation = `/service/`;
    const listPagePath = '/listarservico';

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
                const { id } = match.params;
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
            history.push(listPagePath)
        } catch (error) {
            alert("Ocorreu um erro: " + error);
        }
        console.log(serviceTypeData)
    }

    const handleDelete = async (e) => {
        e.preventDefault()
        try {
            const {id} = match.params;
            const isEditMode = id;
            if (isEditMode) {
                await api.delete(urlOperation+id)
            }
            document.getElementById("deleteServiceTypeModal").classList.remove("show", "d-block");
            document.querySelectorAll(".modal-backdrop")
                .forEach(el => el.classList.remove("modal-backdrop"));
            history.push(listPagePath)
        } catch (error) {
            alert("Ocorreu um erro: " + error);
        }
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
                        <div className="modal-footer">
                            <a href="#deleteServiceTypeModal" className="btn btn-danger" data-toggle="modal">
                                <i className="material-icons">&#xE15C;</i> <span>Remover</span></a>
                            <a href={listPagePath}>
                                <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancelar"/>
                            </a>
                            <input type="submit" className="btn btn-success" value="Salvar"/>
                        </div>
                    </form>
                </div>
            </div>
            <div id="deleteServiceTypeModal" className="modal fade">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <form onSubmit={handleDelete}>
                            <div className="modal-header">
                                <h4 className="modal-title">Remover</h4>
                                <button type="button" className="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button>
                            </div>
                            <div className="modal-body">
                                <p>Você tem certeza que deseja remover?</p>
                                <p className="text-warning"><small>Essa ação não poderá ser desfeita.</small></p>
                            </div>
                            <div className="modal-footer">
                                <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancelar"/>
                                <input type="submit" className="btn btn-danger" value="Remover"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}

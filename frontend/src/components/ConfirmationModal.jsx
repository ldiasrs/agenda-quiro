import api from "../services/api";

export const ConfirmationModal = ({history, deleteApiPath, listRoutePath, id}) => {

    const handleDelete = async (e) => {
        console.log(history)
        e.preventDefault()
        try {
            const isEditMode = id;
            if (isEditMode) {
                await api.delete(`${deleteApiPath}${id}`)
            }
            if (document.getElementById("deleteCustomerModal"))
                document.getElementById("deleteCustomerModal").classList.remove("show", "d-block");
            document.querySelectorAll(".modal-backdrop")
                .forEach(el => {
                    if (el.classList) el.classList.remove("modal-backdrop")
                });
            history.push(listRoutePath)
        } catch (error) {
            console.log(error)
            alert("Ocorreu um erro: " + error);
        }
    }

    return (
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
    );
}

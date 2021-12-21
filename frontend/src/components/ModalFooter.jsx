export const ModalFooter = ({listRoutePath, id}) => {
    const modalId = "#deleteServiceTypeModal-"+id
    console.log(id)
    return (
        <div className="modal-footer">
            <a href={modalId} className="btn btn-danger" data-toggle="modal">
                <i className="material-icons">&#xE15C;</i> <span>Remover</span></a>
            <a href={listRoutePath}>
                <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancelar"/>
            </a>
            <input type="submit" className="btn btn-success" value="Salvar"/>
        </div>
    );
}

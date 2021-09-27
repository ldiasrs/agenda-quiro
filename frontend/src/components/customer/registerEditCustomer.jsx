import {NavigationApp} from "../navigation-app";

export const RegisterEditCustomer = (props) => {

    return (
        <>
            <NavigationApp/>
            <div className="container" className='customForm'>
                <div id="addCustomerModal" className='registerPanel'>
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Cliente</h2>
                            </div>
                        </div>
                    </div>
                    <form>
                        <div className="modal-body">
                            <div className="form-group">
                                <label>Name</label>
                                <input type="text" className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Email</label>
                                <input type="email" className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Phone</label>
                                <input type="text" className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>CPF</label>
                                <input type="text" className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Address</label>
                                <textarea className="form-control" required></textarea>
                            </div>
                            <div className="form-group">
                                <label>Genero</label>
                                <select
                                    value="Genero"
                                    className="form-control">
                                    <option key="male" value="male">
                                        Masculino
                                    </option>
                                    <option key="female" value="female">
                                        Femino
                                    </option>
                                    <option key="other" value="other">
                                        Outro
                                    </option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label>Altura</label>
                                <input type="number" required className="form-control"/>
                            </div>
                            <div className="form-group">
                                <label>Peso</label>
                                <input type="number" required className="form-control"/>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancelar"/>
                            <input type="submit" className="btn btn-success" value="Salvar"/>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}

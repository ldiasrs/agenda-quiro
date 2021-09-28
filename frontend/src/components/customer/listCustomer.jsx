import {NavigationApp} from "../navigation-app";
import {Pagination} from "../Pagination";


export const ListCustomer = (props) => {

    const fetchCustomers = () => {
        let customers = []
        for (let i = 0; i < 10; i++) {
            customers.push(
                {"id":1,"name":"leo"+i,"email":i+"leo@gmail.com","phone":"9039409343","cpf":"0092323","birthDate":{"year":2021,"month":"SEPTEMBER","chronology":{"calendarType":"iso8601","id":"ISO"},"era":"CE","monthValue":9,"dayOfMonth":23,"dayOfYear":266,"dayOfWeek":"THURSDAY","leapYear":false},"height":"92","weight":"178","gender":"masculino"}
            )
        }
        return {
            "totalItems": 10,
            "customers": customers,
            "totalPages": 3,
            "currentPage": 1
        };
    }

    const customerData  = fetchCustomers();

    return (
        <>
            <NavigationApp/>
            <div className='customForm'>
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Clientes</h2>
                            </div>
                            <div className="col-sm-6">
                                <a href="/registrarcliente" className="btn btn-success" data-toggle="modal">
                                    <i className="material-icons">&#xE147;</i> <span>Adicionar</span></a>
                                <a href="#deleteCustomerModal" className="btn btn-danger" data-toggle="modal">
                                    <i className="material-icons">&#xE15C;</i> <span>Deletar</span></a>
                            </div>
                        </div>
                    </div>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>
							<span className="custom-checkbox">
								<input type="checkbox" id="selectAll"/>
								<label htmlFor="selectAll"></label>
							</span>
                            </th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Cep</th>
                            <th>Endereço</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            customerData.customers.map( customer =>
                                <tr>
                                    <td>
                                        <span className="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1"/>
                                            <label htmlFor="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>{ customer.name}</td>
                                    <td>{ customer.email}</td>
                                    <td>{ customer.cpf}</td>
                                    <td>{ customer.phone}</td>
                                    <td>
                                        <a href="/cliente/editar" className="edit" data-toggle="modal"><i
                                            className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deleteCustomerModal" className="delete" data-toggle="modal"><i
                                            className="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            )
                        }

                        </tbody>
                    </table>
                    <Pagination/>
                </div>
            </div>
            <div id="editCustomerModal" className="modal fade">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <form>
                            <div className="modal-header">
                                <h4 className="modal-title">Edit Customer</h4>
                                <button type="button" className="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button>
                            </div>
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
                                    <label>Address</label>
                                    <textarea className="form-control" required></textarea>
                                </div>
                                <div className="form-group">
                                    <label>Phone</label>
                                    <input type="text" className="form-control" required/>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancel"/>
                                <input type="submit" className="btn btn-info" value="Save"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="deleteCustomerModal" className="modal fade">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <form>
                            <div className="modal-header">
                                <h4 className="modal-title">Delete Customer</h4>
                                <button type="button" className="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button>
                            </div>
                            <div className="modal-body">
                                <p>Are you sure you want to delete these Records?</p>
                                <p className="text-warning"><small>This action cannot be undone.</small></p>
                            </div>
                            <div className="modal-footer">
                                <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancel"/>
                                <input type="submit" className="btn btn-danger" value="Delete"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}

import { useState } from 'react'
import {login} from "../services/auth";
import {NavigationApp} from "./navigation-app";
import api from "../services/api";

export const RegisterCustomer = (props) => {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();
    const [error, setError] = useState();

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await api.get("/auth/login", {
                auth: {username: username, password: password
                }
            }).then(response=> login(response.headers.authorization));

            props.history.push("/agenda");
        } catch (err) {
            console.log(err)
            setError("* Houve um problema com o login, verifique suas credenciais.")
        }
    }

    return (
        <>
            <NavigationApp/>
            <div className="container" className='customForm'>
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Cliente</h2>
                            </div>
                            <div className="col-sm-6">
                                <a href="#addCustomerModal" className="btn btn-success" data-toggle="modal">
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
                        <tr>
                            <td>
							<span className="custom-checkbox">
								<input type="checkbox" id="checkbox1" name="options[]" value="1"/>
								<label htmlFor="checkbox1"></label>
							</span>
                            </td>
                            <td>Thomas Hardy</td>
                            <td>thomashardy@mail.com</td>
                            <td>89 Chiaroscuro Rd, Portland, USA</td>
                            <td>(171) 555-2222</td>
                            <td>
                                <a href="#editCustomerModal" className="edit" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                <a href="#deleteCustomerModal" className="delete" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>
							<span className="custom-checkbox">
								<input type="checkbox" id="checkbox2" name="options[]" value="1"/>
								<label htmlFor="checkbox2"></label>
							</span>
                            </td>
                            <td>Dominique Perrier</td>
                            <td>dominiqueperrier@mail.com</td>
                            <td>Obere Str. 57, Berlin, Germany</td>
                            <td>(313) 555-5735</td>
                            <td>
                                <a href="#editCustomerModal" className="edit" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                <a href="#deleteCustomerModal" className="delete" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>
							<span className="custom-checkbox">
								<input type="checkbox" id="checkbox3" name="options[]" value="1"/>
								<label htmlFor="checkbox3"></label>
							</span>
                            </td>
                            <td>Maria Anders</td>
                            <td>mariaanders@mail.com</td>
                            <td>25, rue Lauriston, Paris, France</td>
                            <td>(503) 555-9931</td>
                            <td>
                                <a href="#editCustomerModal" className="edit" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                <a href="#deleteCustomerModal" className="delete" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>
							<span className="custom-checkbox">
								<input type="checkbox" id="checkbox4" name="options[]" value="1"/>
								<label htmlFor="checkbox4"></label>
							</span>
                            </td>
                            <td>Fran Wilson</td>
                            <td>franwilson@mail.com</td>
                            <td>C/ Araquil, 67, Madrid, Spain</td>
                            <td>(204) 619-5731</td>
                            <td>
                                <a href="#editCustomerModal" className="edit" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                <a href="#deleteCustomerModal" className="delete" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                        </tr>
                        <tr>
                            <td>
							<span className="custom-checkbox">
								<input type="checkbox" id="checkbox5" name="options[]" value="1"/>
								<label htmlFor="checkbox5"></label>
							</span>
                            </td>
                            <td>Martin Blank</td>
                            <td>martinblank@mail.com</td>
                            <td>Via Monte Bianco 34, Turin, Italy</td>
                            <td>(480) 631-2097</td>
                            <td>
                                <a href="#editCustomerModal" className="edit" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></a>
                                <a href="#deleteCustomerModal" className="delete" data-toggle="modal"><i
                                    className="material-icons" data-toggle="tooltip" title="Deletar">&#xE872;</i></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div className="clearfix">
                        <div className="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                        <ul className="pagination">
                            <li className="page-item disabled"><a href="#">Previous</a></li>
                            <li className="page-item"><a href="#" className="page-link">1</a></li>
                            <li className="page-item"><a href="#" className="page-link">2</a></li>
                            <li className="page-item active"><a href="#" className="page-link">3</a></li>
                            <li className="page-item"><a href="#" className="page-link">4</a></li>
                            <li className="page-item"><a href="#" className="page-link">5</a></li>
                            <li className="page-item"><a href="#" className="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="addCustomerModal" className="modal fade">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <form>
                            <div className="modal-header">
                                <h4 className="modal-title">Adicionar</h4>
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
                                    <input type="submit" className="btn btn-success" value="Add"/>
                            </div>
                        </form>
                    </div>
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

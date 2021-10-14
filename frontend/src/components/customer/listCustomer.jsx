import {NavigationApp} from "../navigation-app";
import {Pagination} from "../Pagination";
import DatePicker from "react-datepicker";
import {useEffect, useState} from "react";
import { Link } from 'react-router-dom';
import api from "../../services/api";


export const ListCustomer = (props) => {

    const [initialDate, setInitialDate] = useState(new Date());
    const [finalDate, setFinalDate] = useState(new Date());
    const [globalFilter, setGlobalFilter] = useState(undefined);
    const [customerData, setCustomerData] = useState(undefined);

    // com Async Await
    useEffect(() => {
        async function getItems() {
            try {
                const  response  = await fetchCustomers()
                setCustomerData(response.data.content);
            } catch (error) {
                alert("Ocorreu um erro ao buscar os items"+error);
            }
        }
        getItems();
    }, []);

    const fetchCustomers =  () => {
        const params = {
            searchTerm: "",
            page: 0,
            size: 10
        };
        return api.get('/customers', {params})
    }


    const filterCustomer = (customer) => {
        if (!globalFilter) return true;
        console.log(globalFilter)
        return ((customer.name + customer.email + customer.phone + customer.cpf + customer.gender).includes(globalFilter))
    }

    const filterTableElements =  () => {
        if(!customerData) return;
        console.log(customerData)
        return customerData
            .filter(filterCustomer)
            .map(customer =>
                <tr>
                    <td>
                        <span className="custom-checkbox">
                            <input type="checkbox" id="checkbox1" name="options[]" value="1"/>
                            <label htmlFor="checkbox1"></label>
                        </span>
                    </td>
                    <td>{customer.name}</td>
                    <td>{customer.phone}</td>
                    <td>{customer.email}</td>
                    <td>{customer.cpf}</td>
                    <td>{customer.gender}</td>
                    <td>
                        <Link to={`/cliente/${customer.id}`} className="btn btn-sm btn-primary mr-1">Edit</Link>
                    </td>
                </tr>
            )
    }

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
                            </div>
                        </div>
                        <form className="form-inline">
                            <div className="form-group">
                                <label>Data inicial</label>
                                <DatePicker selected={initialDate} onChange={(date) => setInitialDate(date)}/>
                            </div>
                            <div className="form-group">
                                <label>Data final</label>
                                <DatePicker selected={finalDate} onChange={(date) => setFinalDate(date)}/>
                            </div>
                            <div className="form-group">
                                <p>Digite algo para procurar na tabela abaixo:</p>
                                <input className="form-control" id="myInput" type="text" placeholder="Search.."
                                       onChange={e => setGlobalFilter(e.target.value)}/>
                                <button className="btn btn-primary" type="submit">Procurar em todas paginas</button>
                            </div>
                        </form>
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
                            <th>Telefone</th>
                            <th>Email</th>
                            <th>CPF</th>
                            <th>Gênero</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            filterTableElements()
                        }
                        </tbody>
                    </table>
                    <Pagination/>
                </div>
            </div>
        </>
    )
}

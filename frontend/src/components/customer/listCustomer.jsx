import {NavigationApp} from "../navigation-app";
import DatePicker from "react-datepicker";
import {useEffect, useState} from "react";
import {Link} from 'react-router-dom';
import api from "../../services/api";
import {Pagination} from "../Pagination";


export const ListCustomer = (props) => {

    const paginationSize = 10
    const [initialDate, setInitialDate] = useState(new Date());
    const [finalDate, setFinalDate] = useState(new Date());
    const [globalFilter, setGlobalFilter] = useState(undefined);
    const [customerData, setCustomerData] = useState(undefined);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(undefined);
    const [totalElements, setTotalElements] = useState(undefined);

    const handlePrevNavigation = async () => {
        updateCurrentPage(currentPage-1)
    }

    const updateCurrentPage =  (newPage) => {
        if (newPage >= 0 && newPage<totalPages) {
            setCurrentPage(newPage);
        }
    }

    const handleNextNavigation = async () => {
        updateCurrentPage(currentPage+1)
    }

    const fetchItems = async () => {
        try {
            const params = {
                searchTerm: "",
                page: currentPage,
                size: paginationSize
            };
            console.log(params)
            const response = await api.get('/customers', {params})
            setCustomerData(response.data.content);
            setTotalPages(response.data.totalPages)
            setTotalElements(response.data.totalElements)
        } catch (error) {
            console.error("Ocorreu um erro ao buscar os items" + error);
        }
    }

    useEffect(() => {
        async function fetchData() {
            await fetchItems()
        }

        fetchData();
    }, [currentPage]);


    const filterCustomer = (customer) => {
        if (!globalFilter) return true;
        return ((customer.name + customer.email + customer.phone + customer.cpf + customer.gender).includes(globalFilter))
    }

    const filterTableElements = () => {
        if (!customerData) return;
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
                    <Pagination
                        currentPage={currentPage}
                        handlePrevNavigation={handlePrevNavigation}
                        handleNextNavigation={handleNextNavigation}
                        totalElements={totalElements}
                        totalPages={totalPages}
                        maxItemsPerPage={paginationSize}
                    />
                </div>
            </div>
        </>
    )
}

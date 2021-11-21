import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import {Link, useLocation} from 'react-router-dom';
import api from "../../services/api";
import {Pagination} from "../Pagination";

export const ListProfessional = ({ history, match }) => {


    const search = useLocation().search;
    const searchTerm = new URLSearchParams(search).get('searchTerm');

    const paginationSize = 10

    const [tableFilter, setTableFiler] = useState(undefined);
    const [professionalData, setProfessionalData] = useState(undefined);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(undefined);
    const [totalElements, setTotalElements] = useState(undefined);

    const handlePrevNavigation = async () => {
        updateCurrentPage(currentPage-1)
    }

    const updateCurrentPage =  (newPage) => {
        if (newPage >= 0 && newPage<totalPages) {
            setCurrentPage(newPage)
        }
    }

    const handleNextNavigation = async () => {
        updateCurrentPage(currentPage+1)
    }

    const fetchItems = async () => {
        try {
            console.log('searchTerm:' + searchTerm)
            const params = {
                searchTerm: searchTerm,
                page: currentPage,
                size: paginationSize
            };
            console.log(params)
            return await api.get('/professionals', {params})
        } catch (error) {
            console.error("Ocorreu um erro ao buscar os items" + error);
        }
    }

    useEffect(() => {
        async function fetchData() {
            const response = await fetchItems()
            setTotalPages(response.data.totalPages)
            setTotalElements(response.data.totalElements)
            setProfessionalData(response.data.content)
        }
        fetchData();
    }, [currentPage]);

    const filterCustomer = (professional) => {
        if (!tableFilter) return true;
        return ((professional.name).includes(tableFilter))
    }

    const filterTableElements = () => {
        let key = 0
        if (!professionalData) return;
        return professionalData
            .filter(filterCustomer)
            .map(professional =>
                <tr key={key++}>
                    {/*<td>*/}
                    {/*    <span className="custom-checkbox">*/}
                    {/*        <input type="checkbox" id="checkbox1" name="options[]" value="1"/>*/}
                    {/*        <label htmlFor="checkbox1"></label>*/}
                    {/*    </span>*/}
                    {/*</td>*/}
                    <td>{professional.name}</td>
                    <td>
                        <Link to={`/professional/${professional.id}`} className="btn btn-sm btn-primary mr-1">Edit</Link>
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
                                <h2>Professionais</h2>
                            </div>
                            <div className="col-sm-6">
                                <a href="/registrarprofessional" className="btn btn-success" data-toggle="modal">
                                    <i className="material-icons">&#xE147;</i> <span>Adicionar</span></a>
                            </div>
                        </div>
                        <form className="form-inline">
                            <div className="form-group">
                                <p>Digite algo para procurar na tabela abaixo:</p>
                                <input className="form-control" id="myInput" type="text" placeholder="Search.."
                                       onChange={e => setTableFiler(e.target.value)}/>
                                <a href={`/listarcliente?searchTerm=${tableFilter || ''}`} className="btn btn-primary">Procurar em todas paginas</a>
                            </div>
                        </form>
                    </div>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            {/*<th>*/}
							{/*<span className="custom-checkbox">*/}
							{/*	<input type="checkbox" id="selectAll"/>*/}
							{/*	<label htmlFor="selectAll"></label>*/}
							{/*</span>*/}
                            {/*</th>*/}
                            <th>Nome</th>
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

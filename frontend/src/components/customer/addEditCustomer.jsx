import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import Select from 'react-select';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import api from "../../services/api";

export const AddEditCustomer = ({ history, match }) => {

    // com Async Await
    useEffect(() => {
        async function getItem() {
            try {
                const { id } = match.params;
                console.log("id: " + id)
                const isEditMode = id;
                if (isEditMode) {
                    const response = await api.get(`/customer/${id}`)
                    const customer = response.data;
                    setName(customer.name)
                    setEmail(customer.email)
                    setPhone(customer.phone)
                    setCpf(customer.cpf)
                    setBirthDate(new Date(customer.birthDate))
                    setHeight(customer.height)
                    setWeight(customer.weight)
                    setGender(customer.gender)
                    setId(customer.id)
                }
            } catch (error) {
                alert("Ocorreu um erro ao buscar o item: "+error);
            }
        }
        getItem();
    }, []);


    const defaultvalues = {
        id : undefined,
        name: "",
        email : "",
        phone: "",
        cpf: "",
        birthDate : new Date(),
        height : 0,
        weight : 0,
        gender : "male"
    }

    const [name, setName] = useState(defaultvalues.name);
    const [email, setEmail] = useState(defaultvalues.email);
    const [phone, setPhone] = useState(defaultvalues.phone);
    const [cpf, setCpf] = useState(defaultvalues.cpf);
    const [birthDate, setBirthDate] = useState(defaultvalues.birthDate);
    const [height, setHeight] = useState(defaultvalues.height);
    const [weight, setWeight] = useState(defaultvalues.weight);
    const [gender, setGender] = useState(defaultvalues.gender);
    const [id, setId] = useState(defaultvalues.id);

    const handleSubmit = async (e) => {
        e.preventDefault()
        const formattedDate = birthDate.toISOString().split('T')[0]
        const customerData = {
            name,
            email,
            phone,
            cpf,
            birthDate: formattedDate,
            height,
            weight,
            gender
        }
        try {
            const {id} = match.params;
            const isEditMode = id;
            if (isEditMode) {
                await api.put(`/customer/${id}`, customerData)
            } else {
                await api.post(`/customers`, customerData)
            }
            history.push('/listarcliente')
        } catch (error) {
            alert("Ocorreu um erro: " + error);
        }
        console.log(customerData)
    }

    const handleDelete = async (e) => {
        e.preventDefault()
        try {
            const {id} = match.params;
            const isEditMode = id;
            if (isEditMode) {
                await api.delete(`/customer/${id}`)
            }
            history.push('/listarcliente')
        } catch (error) {
            alert("Ocorreu um erro: " + error);
        }
    }

    const genderOptions = [
        { value: 'male', label: 'Masculino' },
        { value: 'female', label: 'Feminino' },
        { value: 'other', label: 'Outro' },
    ]

    const handleSelectGenderChange = (selectedOption) => {
        setGender(selectedOption.value)
    };

    return (
        <>
            <NavigationApp/>
            <div  className='customForm'>
                <div id="addCustomerModal" className='registerPanel'>
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Cliente</h2>
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
                            <div className="form-group">
                                <label>Email</label>
                                <input type="email"
                                       value={email}
                                       onChange={e => setEmail(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Phone</label>
                                <input type="text"
                                       value={phone}
                                       onChange={e => setPhone(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>CPF</label>
                                <input type="text"
                                       value={cpf}
                                       onChange={e => setCpf(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div >
                                <label>Gênero</label>
                                <br></br>
                            </div>
                            <div className="form-group">
                                <br></br>
                                <Select
                                    onChange={handleSelectGenderChange}
                                    options={genderOptions}
                                    value = {
                                        genderOptions.filter(option =>
                                            option.value === gender)
                                    }
                                />
                            </div>
                            <div className="form-group">
                                <label>Data de nascimento</label>
                                <DatePicker dateFormat="dd/MM/yyyy" selected={birthDate} onChange={(date) => setBirthDate(date)} />
                            </div>
                            <div className="form-group">
                                <label>Altura</label>
                                <input type="number"
                                       value={height}
                                       onChange={e => setHeight(e.target.value)}
                                       required className="form-control"/>
                            </div>
                            <div className="form-group">
                                <label>Peso</label>
                                <input type="number"
                                       value={weight}
                                       onChange={e => setWeight(e.target.value)}
                                       required className="form-control"/>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <a href="#deleteCustomerModal" className="btn btn-danger" data-toggle="modal">
                                <i className="material-icons">&#xE15C;</i> <span>Remover</span></a>
                            <a href='/listarcliente'>
                                <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancelar"/>
                            </a>
                            <input type="submit" className="btn btn-success" value="Salvar"/>
                        </div>
                    </form>
                </div>
            </div>
            <div id="deleteCustomerModal" className="modal fade">
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

import {NavigationApp} from "../navigation-app";
import {useState} from "react";
import Select from 'react-select';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export const AddEditCustomer = ({ history, match }) => {

    const fetchCustomer = (id) => {
        return   {
            name: "leo",
            email : "leo@gmail.com",
            phone: "999",
            cpf: "222",
            birthDate : new Date(),
            height : 0,
            weight : 0,
            gender : "male"
        }
    }

    const defaultvalues = {
        name: "",
        email : "",
        phone: "",
        cpf: "",
        birthDate : new Date(),
        height : 0,
        weight : 0,
        gender : "male"
    }

    const { id } = match.params;
    const isAddMode = !id;

    const customerData = isAddMode ? defaultvalues  : fetchCustomer(id);

    const [name, setName] = useState(customerData.name);
    const [email, setEmail] = useState(customerData.email);
    const [phone, setPhone] = useState(customerData.phone);
    const [cpf, setCpf] = useState(customerData.cpf);
    const [birthDate, setBirthDate] = useState(customerData.birthDate);
    const [height, setHeight] = useState(customerData.height);
    const [weight, setWeight] = useState(customerData.weight);
    const [gender, setGender] = useState(customerData.gender);

    const handleSubmit = (e) => {
        e.preventDefault()
        const customerData = {
            name,
            email,
            phone,
            cpf,
            birthDate,
            height,
            weight,
            gender
        }
        console.log(customerData)
    }

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
                                    options={[
                                        { value: 'male', label: 'Masculino' },
                                        { value: 'female', label: 'Feminino' },
                                        { value: 'other', label: 'Outro' },
                                    ]}
                                />
                            </div>
                            <div className="form-group">
                                <label>Data de nascimento</label>
                                <DatePicker selected={birthDate} onChange={(date) => setBirthDate(date)} />
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
                        <form>
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

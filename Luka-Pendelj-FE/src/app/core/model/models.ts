export interface Organization {
  organizationId: number;
  organizationCode?: string;
  active: boolean;
  organizationType: OrganizationType;
  name: string;
  address?: string;
  phone?: string;
  email?: string;
}

export interface OrganizationType {
  id: number;
  name: string;
}

//Qualifications:
//[‘Doctor of Medicine’, ‘Medical Assistant’, ‘Nurse Practitioner’, ‘Doctor of Pharmacy’, ‘Certified Nurse Midwife’, ‘Emergency Medical Technician’]

export interface Medic {
  medicId: number;
  medicCode?: string;
  active: boolean;
  firstname: string;
  lastname: string;
  gender: Gender;
  birthDate: Date;
  address?: string;
  phone?: string;
  email?: string;
  qualification: string;
  organization?: Organization;
}

//marital status:
//[‘Annulled’, ‘Divorced’, ‘Married’, ‘Polygamous’, ‘Never Married’, ‘Widowed’]

export interface Patient {
  patientId: number;
  patientCode?: string;
  active: Boolean;
  firstname: string;
  lastname: string;
  gender: Gender;
  birthDate: Date;
  address?: string;
  phone?: string;
  email?: string;
  deceased?: boolean;
  maritalStatus?: string;
  mainMedic?: Medic;
  organization?: Organization;
}

//[‘asap’, ‘callback results’, ‘emergency’, ‘routine’, ‘rush reporting’, ‘timing critical’]

//[‘planned’, ‘triaged’, ‘in-progress’, ‘suspended’,‘finished’, ‘cancelled’, ’entered-in-error’]
export interface Examination {
  examinationId: number;
  examinationCode?: string;
  status: string;
  serviceType: ServiceType;
  priority?: string;
  startDate?: Date;
  endDate?: Date;
  diagnosis?: string;
  organization?: Organization;
  patient?: Patient;
  medicList?: Medic[];
}

export interface ServiceType {
  serviceId: number,
  serviceName: string
}

export interface Gender {
  genderCode: string,
  genderName: string
}
